package nc.recipe;

import java.util.ArrayList;
import java.util.List;

import nc.util.OreDictHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

public class RecipeOreStack implements IIngredient, IRecipeStack {
	
	public String oreString;
	public boolean isFluid;
	public ArrayList<ItemStack> cachedItemRegister;
	public ArrayList<FluidStack> cachedFluidRegister;
	public int stackSize;
	public StackType type;

	public RecipeOreStack(String oreType, StackType stacktype, int stackSize) {
		oreString = oreType;
		//cachedItemRegister = new ArrayList<ItemStack>(OreDictionary.getOres(oreType));
		cachedItemRegister = OreDictHelper.getPrioritisedStackList(oreType);
		cachedFluidRegister = new ArrayList<FluidStack>();
		ArrayList<Fluid> fluidList = new ArrayList<Fluid>(FluidRegistry.getRegisteredFluids().values());
		for (Fluid fluid : fluidList) {
			if (fluid.getName() == oreType.toLowerCase()) cachedFluidRegister.add(new FluidStack(fluid, stackSize));
		}
		if (cachedFluidRegister.isEmpty() && stacktype.isFluid()) {
			if (FluidRegistry.getFluid(oreType.toLowerCase()) != null) cachedFluidRegister.add(new FluidStack(FluidRegistry.getFluid(oreType.toLowerCase()), stackSize));
		}
		if (!stacktype.isItem() && !stacktype.isFluid()) {
			if (cachedItemRegister.isEmpty()) isFluid = true; else isFluid = false;
		}
		else isFluid = stacktype.isFluid();
		this.stackSize = stackSize;
		type = stacktype;
	}

	@Override
	public Object getIngredient() {
		if (isFluid) {
			if (cachedFluidRegister.size() < 1) return cachedFluidRegister;
			FluidStack fluid = cachedFluidRegister.get(0).copy();
			fluid.amount = stackSize;
			return fluid;
		}
		if (cachedItemRegister.size() < 1) return cachedItemRegister;
		ItemStack item = cachedItemRegister.get(0).copy();
		item.setCount(stackSize);
		return item;
	}
	
	@Override
	public String getIngredientName() {
		if (isFluid) return "fluid:" + oreString;
		return "ore:" + oreString;
	}
	
	@Override
	public String getIngredientNames() {
		return getIngredientName();
	}

	@Override
	public StackType getIngredientType() {
		return type;
	}

	@Override
	public Object getOutputStack() {
		Object stack = isFluid ? cachedFluidRegister.get(0).copy() : cachedItemRegister.get(0).copy();
		if (isFluid) {
			FluidStack fluidstack = (FluidStack)stack;
			fluidstack.amount = stackSize;
			return fluidstack;
		}
		ItemStack itemstack = (ItemStack)stack;
		itemstack.setCount(stackSize);
		return itemstack;
	}

	@Override
	public boolean matches(Object object, SorptionType type) {
		if (object instanceof RecipeOreStack) {
			RecipeOreStack oreStack = (RecipeOreStack)object;
			if (oreStack.oreString.equals(oreString) && oreStack.stackSize >= stackSize) {
				return true;
			}
		}
		else if (object instanceof String) {
			return oreString.equals(object);
		}
		else if (object instanceof ItemStack && type.checkStackSize(stackSize, ((ItemStack) object).getCount())) {
			int oreID = OreDictionary.getOreID(oreString);
			for (int ID : OreDictionary.getOreIDs((ItemStack)object)) {
				if (oreID == ID) {
					return true;
				}
			}
		}
		else if (object instanceof FluidStack && type.checkStackSize(stackSize, ((FluidStack) object).amount)) {
			String fluidName = FluidRegistry.getFluidName((FluidStack)object);
			if (oreString.equals(fluidName)) {
				return true;
			}
		}
		else if (object instanceof RecipeStack) {
			if (matches(((RecipeStack) object).stack, type)) return true;
		}
		else if (object instanceof RecipeStackArray) {
			for (IIngredient ingredient : ((RecipeStackArray) object).validInputList) if (!matches(ingredient, type)) return false;
			return true;
		}
		return false;
	}

	@Override
	public int getStackSize() {
		return stackSize;
	}

	@Override
	public List<Object> getIngredientList() {
		if (isFluid) {
			List<Object> fluidCollection = new ArrayList<Object>();
			for (FluidStack fluid : cachedFluidRegister) {
				FluidStack fluidStack = fluid.copy();
				fluidStack.amount = stackSize;
				fluidCollection.add(fluidStack);
			}
			return fluidCollection;
		}
		List<Object> itemCollection = new ArrayList<Object>();
		for (ItemStack item : cachedItemRegister) {
			ItemStack itemStack = item.copy();
			itemStack.setCount(stackSize);
			itemCollection.add(itemStack);
		}
		return itemCollection;
	}
}
