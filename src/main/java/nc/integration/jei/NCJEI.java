package nc.integration.jei;

import java.util.ArrayList;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import nc.container.generator.ContainerFissionController;
import nc.container.generator.ContainerFusionCore;
import nc.container.processor.ContainerAlloyFurnace;
import nc.container.processor.ContainerCentrifuge;
import nc.container.processor.ContainerChemicalReactor;
import nc.container.processor.ContainerCrystallizer;
import nc.container.processor.ContainerDecayHastener;
import nc.container.processor.ContainerDissolver;
import nc.container.processor.ContainerElectrolyser;
import nc.container.processor.ContainerExtractor;
import nc.container.processor.ContainerFuelReprocessor;
import nc.container.processor.ContainerInfuser;
import nc.container.processor.ContainerIngotFormer;
import nc.container.processor.ContainerIrradiator;
import nc.container.processor.ContainerIsotopeSeparator;
import nc.container.processor.ContainerManufactory;
import nc.container.processor.ContainerMelter;
import nc.container.processor.ContainerPressurizer;
import nc.container.processor.ContainerRockCrusher;
import nc.container.processor.ContainerSaltMixer;
import nc.container.processor.ContainerSupercooler;
import nc.enumm.MetaEnums;
import nc.gui.generator.GuiFissionController;
import nc.gui.generator.GuiFusionCore;
import nc.gui.processor.GuiAlloyFurnace;
import nc.gui.processor.GuiCentrifuge;
import nc.gui.processor.GuiChemicalReactor;
import nc.gui.processor.GuiCrystallizer;
import nc.gui.processor.GuiDecayHastener;
import nc.gui.processor.GuiDissolver;
import nc.gui.processor.GuiElectrolyser;
import nc.gui.processor.GuiExtractor;
import nc.gui.processor.GuiFuelReprocessor;
import nc.gui.processor.GuiInfuser;
import nc.gui.processor.GuiIngotFormer;
import nc.gui.processor.GuiIrradiator;
import nc.gui.processor.GuiIsotopeSeparator;
import nc.gui.processor.GuiManufactory;
import nc.gui.processor.GuiMelter;
import nc.gui.processor.GuiPressurizer;
import nc.gui.processor.GuiRockCrusher;
import nc.gui.processor.GuiSaltMixer;
import nc.gui.processor.GuiSupercooler;
import nc.init.NCBlocks;
import nc.init.NCItems;
import nc.integration.jei.generator.DecayGeneratorCategory;
import nc.integration.jei.generator.FissionCategory;
import nc.integration.jei.generator.FusionCategory;
import nc.integration.jei.processor.AlloyFurnaceCategory;
import nc.integration.jei.processor.CentrifugeCategory;
import nc.integration.jei.processor.ChemicalReactorCategory;
import nc.integration.jei.processor.CrystallizerCategory;
import nc.integration.jei.processor.DecayHastenerCategory;
import nc.integration.jei.processor.DissolverCategory;
import nc.integration.jei.processor.ElectrolyserCategory;
import nc.integration.jei.processor.ExtractorCategory;
import nc.integration.jei.processor.FuelReprocessorCategory;
import nc.integration.jei.processor.InfuserCategory;
import nc.integration.jei.processor.IngotFormerCategory;
import nc.integration.jei.processor.IrradiatorCategory;
import nc.integration.jei.processor.IsotopeSeparatorCategory;
import nc.integration.jei.processor.ManufactoryCategory;
import nc.integration.jei.processor.MelterCategory;
import nc.integration.jei.processor.PressurizerCategory;
import nc.integration.jei.processor.RockCrusherCategory;
import nc.integration.jei.processor.SaltMixerCategory;
import nc.integration.jei.processor.SupercoolerCategory;
import nc.integration.jei.saltFission.CoolantHeaterCategory;
import nc.integration.jei.saltFission.SaltFissionCategory;
import nc.recipe.BaseRecipeHandler;
import nc.recipe.IRecipe;
import nc.recipe.NCRecipes;
import nc.util.ItemStackHelper;
import nc.util.NCUtil;
import nc.worldgen.ore.OreGenerator;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;

@JEIPlugin
public class NCJEI implements IModPlugin, IJEIRecipeBuilder {
	
	{
		JEIMethods.registerRecipeBuilder(this);
	}
	
	@Override
	public void register(IModRegistry registry) {
		IJeiHelpers jeiHelpers = registry.getJeiHelpers();
		IGuiHelper guiHelper = jeiHelpers.getGuiHelper();
		
		for (IJEIHandler handler : Handlers.values()) {
			registry.addRecipes(handler.getJEIRecipes());
			JEICategory category = handler.getCategory(guiHelper);
			registry.addRecipeCategories(category);
			registry.addRecipeHandlers(category);
			if (handler.getCrafterItemStack() != null) registry.addRecipeCatalyst(handler.getCrafterItemStack(), handler.getUUID());
		}
		IRecipeTransferRegistry recipeTransferRegistry = registry.getRecipeTransferRegistry();
		
		registry.addRecipeClickArea(GuiManufactory.class, 73, 34, 37, 18, Handlers.MANUFACTORY.getUUID());
		registry.addRecipeClickArea(GuiIsotopeSeparator.class, 59, 34, 37, 18, Handlers.ISOTOPE_SEPARATOR.getUUID());
		registry.addRecipeClickArea(GuiDecayHastener.class, 73, 34, 37, 18, Handlers.DECAY_HASTENER.getUUID());
		registry.addRecipeClickArea(GuiFuelReprocessor.class, 67, 30, 37, 38, Handlers.FUEL_REPROCESSOR.getUUID());
		registry.addRecipeClickArea(GuiAlloyFurnace.class, 83, 34, 37, 18, Handlers.ALLOY_FURNACE.getUUID());
		registry.addRecipeClickArea(GuiInfuser.class, 83, 34, 37, 18, Handlers.INFUSER.getUUID());
		registry.addRecipeClickArea(GuiMelter.class, 73, 34, 37, 18, Handlers.MELTER.getUUID());
		registry.addRecipeClickArea(GuiSupercooler.class, 73, 34, 37, 18, Handlers.SUPERCOOLER.getUUID());
		registry.addRecipeClickArea(GuiElectrolyser.class, 67, 30, 37, 38, Handlers.ELECTROLYSER.getUUID());
		registry.addRecipeClickArea(GuiIrradiator.class, 69, 34, 37, 18, Handlers.IRRADIATOR.getUUID());
		registry.addRecipeClickArea(GuiIngotFormer.class, 73, 34, 37, 18, Handlers.INGOT_FORMER.getUUID());
		registry.addRecipeClickArea(GuiPressurizer.class, 73, 34, 37, 18, Handlers.PRESSURIZER.getUUID());
		registry.addRecipeClickArea(GuiChemicalReactor.class, 69, 34, 37, 18, Handlers.CHEMICAL_REACTOR.getUUID());
		registry.addRecipeClickArea(GuiSaltMixer.class, 83, 34, 37, 18, Handlers.SALT_MIXER.getUUID());
		registry.addRecipeClickArea(GuiCrystallizer.class, 73, 34, 37, 18, Handlers.CRYSTALLIZER.getUUID());
		registry.addRecipeClickArea(GuiDissolver.class, 83, 34, 37, 18, Handlers.DISSOLVER.getUUID());
		registry.addRecipeClickArea(GuiExtractor.class, 59, 34, 37, 18, Handlers.EXTRACTOR.getUUID());
		registry.addRecipeClickArea(GuiCentrifuge.class, 67, 30, 37, 38, Handlers.CENTRIFUGE.getUUID());
		registry.addRecipeClickArea(GuiRockCrusher.class, 55, 34, 37, 18, Handlers.ROCK_CRUSHER.getUUID());
		registry.addRecipeClickArea(GuiFissionController.class, 73, 34, 37, 18, Handlers.FISSION.getUUID());
		registry.addRecipeClickArea(GuiFusionCore.class, 47, 5, 121, 97, Handlers.FUSION.getUUID());
		
		recipeTransferRegistry.addRecipeTransferHandler(ContainerManufactory.class, Handlers.MANUFACTORY.getUUID(), 0, 1, 4, 36);
		recipeTransferRegistry.addRecipeTransferHandler(ContainerIsotopeSeparator.class, Handlers.ISOTOPE_SEPARATOR.getUUID(), 0, 1, 5, 36);
		recipeTransferRegistry.addRecipeTransferHandler(ContainerDecayHastener.class, Handlers.DECAY_HASTENER.getUUID(), 0, 1, 4, 36);
		recipeTransferRegistry.addRecipeTransferHandler(ContainerFuelReprocessor.class, Handlers.FUEL_REPROCESSOR.getUUID(), 0, 1, 7, 36);
		recipeTransferRegistry.addRecipeTransferHandler(ContainerAlloyFurnace.class, Handlers.ALLOY_FURNACE.getUUID(), 0, 2, 5, 36);
		recipeTransferRegistry.addRecipeTransferHandler(ContainerInfuser.class, Handlers.INFUSER.getUUID(), 0, 1, 4, 36);
		recipeTransferRegistry.addRecipeTransferHandler(ContainerMelter.class, Handlers.MELTER.getUUID(), 0, 1, 3, 36);
		recipeTransferRegistry.addRecipeTransferHandler(ContainerSupercooler.class, Handlers.SUPERCOOLER.getUUID(), 0, 0, 2, 36);
		recipeTransferRegistry.addRecipeTransferHandler(ContainerElectrolyser.class, Handlers.ELECTROLYSER.getUUID(), 0, 0, 2, 36);
		recipeTransferRegistry.addRecipeTransferHandler(ContainerIrradiator.class, Handlers.IRRADIATOR.getUUID(), 0, 0, 2, 36);
		recipeTransferRegistry.addRecipeTransferHandler(ContainerIngotFormer.class, Handlers.INGOT_FORMER.getUUID(), 0, 0, 3, 36);
		recipeTransferRegistry.addRecipeTransferHandler(ContainerPressurizer.class, Handlers.PRESSURIZER.getUUID(), 0, 1, 4, 36);
		recipeTransferRegistry.addRecipeTransferHandler(ContainerChemicalReactor.class, Handlers.CHEMICAL_REACTOR.getUUID(), 0, 0, 2, 36);
		recipeTransferRegistry.addRecipeTransferHandler(ContainerSaltMixer.class, Handlers.SALT_MIXER.getUUID(), 0, 0, 2, 36);
		recipeTransferRegistry.addRecipeTransferHandler(ContainerCrystallizer.class, Handlers.CRYSTALLIZER.getUUID(), 0, 0, 3, 36);
		recipeTransferRegistry.addRecipeTransferHandler(ContainerDissolver.class, Handlers.DISSOLVER.getUUID(), 0, 1, 3, 36);
		recipeTransferRegistry.addRecipeTransferHandler(ContainerExtractor.class, Handlers.EXTRACTOR.getUUID(), 0, 1, 4, 36);
		recipeTransferRegistry.addRecipeTransferHandler(ContainerCentrifuge.class, Handlers.CENTRIFUGE.getUUID(), 0, 0, 2, 36);
		recipeTransferRegistry.addRecipeTransferHandler(ContainerRockCrusher.class, Handlers.ROCK_CRUSHER.getUUID(), 0, 1, 6, 36);
		recipeTransferRegistry.addRecipeTransferHandler(ContainerFissionController.class, Handlers.FISSION.getUUID(), 0, 1, 3, 36);
		recipeTransferRegistry.addRecipeTransferHandler(ContainerFusionCore.class, Handlers.FUSION.getUUID(), 0, 0, 0, 36);
		
		for (int i = 0; i < 8; i++) {
			if (!OreGenerator.showOre(i)) {
				blacklist(jeiHelpers, new ItemStack(NCBlocks.ore, 1, i));
				blacklist(jeiHelpers, new ItemStack(NCBlocks.ingot_block, 1, i));
				blacklist(jeiHelpers, new ItemStack(NCItems.ingot, 1, i));
				blacklist(jeiHelpers, new ItemStack(NCItems.dust, 1, i));
			}
		}
		
		blacklist(jeiHelpers, NCBlocks.reactor_door);
		
		blacklist(jeiHelpers, NCBlocks.nuclear_furnace_active);
		blacklist(jeiHelpers, NCBlocks.manufactory_active);
		blacklist(jeiHelpers, NCBlocks.isotope_separator_active);
		blacklist(jeiHelpers, NCBlocks.decay_hastener_active);
		blacklist(jeiHelpers, NCBlocks.fuel_reprocessor_active);
		blacklist(jeiHelpers, NCBlocks.alloy_furnace_active);
		blacklist(jeiHelpers, NCBlocks.infuser_active);
		blacklist(jeiHelpers, NCBlocks.melter_active);
		blacklist(jeiHelpers, NCBlocks.supercooler_active);
		blacklist(jeiHelpers, NCBlocks.electrolyser_active);
		blacklist(jeiHelpers, NCBlocks.irradiator_active);
		blacklist(jeiHelpers, NCBlocks.ingot_former_active);
		blacklist(jeiHelpers, NCBlocks.pressurizer_active);
		blacklist(jeiHelpers, NCBlocks.chemical_reactor_active);
		blacklist(jeiHelpers, NCBlocks.salt_mixer_active);
		blacklist(jeiHelpers, NCBlocks.crystallizer_active);
		blacklist(jeiHelpers, NCBlocks.dissolver_active);
		blacklist(jeiHelpers, NCBlocks.extractor_active);
		blacklist(jeiHelpers, NCBlocks.centrifuge_active);
		blacklist(jeiHelpers, NCBlocks.rock_crusher_active);
		
		blacklist(jeiHelpers, NCBlocks.fission_controller_active);
		blacklist(jeiHelpers, NCBlocks.fission_controller_new_active);
		
		blacklist(jeiHelpers, NCBlocks.fusion_dummy_side);
		blacklist(jeiHelpers, NCBlocks.fusion_dummy_top);
		
		blacklist(jeiHelpers, NCBlocks.fusion_electromagnet_active);
		blacklist(jeiHelpers, NCBlocks.fusion_electromagnet_transparent_active);
		blacklist(jeiHelpers, NCBlocks.accelerator_electromagnet_active);
		blacklist(jeiHelpers, NCBlocks.electromagnet_supercooler_active);
		
		blacklist(jeiHelpers, NCItems.fuel_rod_empty);
		
		blacklistAll(jeiHelpers, MetaEnums.ThoriumFuelRodType.class, NCItems.fuel_rod_thorium);
		blacklistAll(jeiHelpers, MetaEnums.UraniumFuelRodType.class, NCItems.fuel_rod_uranium);
		blacklistAll(jeiHelpers, MetaEnums.NeptuniumFuelRodType.class, NCItems.fuel_rod_neptunium);
		blacklistAll(jeiHelpers, MetaEnums.PlutoniumFuelRodType.class, NCItems.fuel_rod_plutonium);
		blacklistAll(jeiHelpers, MetaEnums.MixedOxideFuelRodType.class, NCItems.fuel_rod_mixed_oxide);
		blacklistAll(jeiHelpers, MetaEnums.AmericiumFuelRodType.class, NCItems.fuel_rod_americium);
		blacklistAll(jeiHelpers, MetaEnums.CuriumFuelRodType.class, NCItems.fuel_rod_curium);
		blacklistAll(jeiHelpers, MetaEnums.BerkeliumFuelRodType.class, NCItems.fuel_rod_berkelium);
		blacklistAll(jeiHelpers, MetaEnums.CaliforniumFuelRodType.class, NCItems.fuel_rod_californium);
		
		blacklistAll(jeiHelpers, MetaEnums.ThoriumDepletedFuelRodType.class, NCItems.depleted_fuel_rod_thorium);
		blacklistAll(jeiHelpers, MetaEnums.UraniumDepletedFuelRodType.class, NCItems.depleted_fuel_rod_uranium);
		blacklistAll(jeiHelpers, MetaEnums.NeptuniumDepletedFuelRodType.class, NCItems.depleted_fuel_rod_neptunium);
		blacklistAll(jeiHelpers, MetaEnums.PlutoniumDepletedFuelRodType.class, NCItems.depleted_fuel_rod_plutonium);
		blacklistAll(jeiHelpers, MetaEnums.MixedOxideDepletedFuelRodType.class, NCItems.depleted_fuel_rod_mixed_oxide);
		blacklistAll(jeiHelpers, MetaEnums.AmericiumDepletedFuelRodType.class, NCItems.depleted_fuel_rod_americium);
		blacklistAll(jeiHelpers, MetaEnums.CuriumDepletedFuelRodType.class, NCItems.depleted_fuel_rod_curium);
		blacklistAll(jeiHelpers, MetaEnums.BerkeliumDepletedFuelRodType.class, NCItems.depleted_fuel_rod_berkelium);
		blacklistAll(jeiHelpers, MetaEnums.CaliforniumDepletedFuelRodType.class, NCItems.depleted_fuel_rod_californium);
		
		NCUtil.getLogger().info("JEI integration complete");
	}
	
	@Override
	public Object buildRecipe(IRecipe recipe, BaseRecipeHandler<IRecipe> methods) {
		if ((Loader.isModLoaded("jei") || Loader.isModLoaded("JEI"))) {
			for (Handlers handler : NCJEI.Handlers.values()) {
				if (handler.getRecipeHandler().getRecipeName().equals(methods.getRecipeName())) {
					try {
						return handler.recipeClass.getConstructor(BaseRecipeHandler.class, IRecipe.class).newInstance(handler.getRecipeHandler(), recipe);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}
	
	private void blacklist(IJeiHelpers jeiHelpers, Object ingredient) {
		if (ingredient == null) return;
		jeiHelpers.getIngredientBlacklist().addIngredientToBlacklist(ItemStackHelper.fixItemStack(ingredient));
	}
	
	private <T extends Enum<T>> void blacklistAll(IJeiHelpers jeiHelpers, Class<T> enumm, Block block) {
		if (block == null) return;
		for (int i = 0; i < enumm.getEnumConstants().length; i++) blacklist(jeiHelpers, new ItemStack(block, 1, i));
	}
	
	private <T extends Enum<T>> void blacklistAll(IJeiHelpers jeiHelpers, Class<T> enumm, Item item) {
		if (item == null) return;
		for (int i = 0; i < enumm.getEnumConstants().length; i++) blacklist(jeiHelpers, new ItemStack(item, 1, i));
	}
	
	public enum Handlers implements IJEIHandler {
		MANUFACTORY(NCRecipes.Type.MANUFACTORY, NCBlocks.manufactory_idle, "manufactory", RecipesJEI.Manufactory.class),
		ISOTOPE_SEPARATOR(NCRecipes.Type.ISOTOPE_SEPARATOR, NCBlocks.isotope_separator_idle, "isotope_separator", RecipesJEI.IsotopeSeparator.class),
		DECAY_HASTENER(NCRecipes.Type.DECAY_HASTENER, NCBlocks.decay_hastener_idle, "decay_hastener", RecipesJEI.DecayHastener.class),
		FUEL_REPROCESSOR(NCRecipes.Type.FUEL_REPROCESSOR, NCBlocks.fuel_reprocessor_idle, "fuel_reprocessor", RecipesJEI.FuelReprocessor.class),
		ALLOY_FURNACE(NCRecipes.Type.ALLOY_FURNACE, NCBlocks.alloy_furnace_idle, "alloy_furnace", RecipesJEI.AlloyFurnace.class),
		INFUSER(NCRecipes.Type.INFUSER, NCBlocks.infuser_idle, "infuser", RecipesJEI.Infuser.class),
		MELTER(NCRecipes.Type.MELTER, NCBlocks.melter_idle, "melter", RecipesJEI.Melter.class),
		SUPERCOOLER(NCRecipes.Type.SUPERCOOLER, NCBlocks.supercooler_idle, "supercooler", RecipesJEI.Supercooler.class),
		ELECTROLYSER(NCRecipes.Type.ELECTROLYSER, NCBlocks.electrolyser_idle, "electrolyser", RecipesJEI.Electrolyser.class),
		IRRADIATOR(NCRecipes.Type.IRRADIATOR, NCBlocks.irradiator_idle, "irradiator", RecipesJEI.Irradiator.class),
		INGOT_FORMER(NCRecipes.Type.INGOT_FORMER, NCBlocks.ingot_former_idle, "ingot_former", RecipesJEI.IngotFormer.class),
		PRESSURIZER(NCRecipes.Type.PRESSURIZER, NCBlocks.pressurizer_idle, "pressurizer", RecipesJEI.Pressurizer.class),
		CHEMICAL_REACTOR(NCRecipes.Type.CHEMICAL_REACTOR, NCBlocks.chemical_reactor_idle, "chemical_reactor", RecipesJEI.ChemicalReactor.class),
		SALT_MIXER(NCRecipes.Type.SALT_MIXER, NCBlocks.salt_mixer_idle, "salt_mixer", RecipesJEI.SaltMixer.class),
		CRYSTALLIZER(NCRecipes.Type.CRYSTALLIZER, NCBlocks.crystallizer_idle, "crystallizer", RecipesJEI.Crystallizer.class),
		DISSOLVER(NCRecipes.Type.DISSOLVER, NCBlocks.dissolver_idle, "dissolver", RecipesJEI.Dissolver.class),
		EXTRACTOR(NCRecipes.Type.EXTRACTOR, NCBlocks.extractor_idle, "extractor", RecipesJEI.Extractor.class),
		CENTRIFUGE(NCRecipes.Type.CENTRIFUGE, NCBlocks.centrifuge_idle, "centrifuge", RecipesJEI.Centrifuge.class),
		ROCK_CRUSHER(NCRecipes.Type.ROCK_CRUSHER, NCBlocks.rock_crusher_idle, "rock_crusher", RecipesJEI.RockCrusher.class),
		DECAY_GENERATOR(NCRecipes.Type.DECAY_GENERATOR, NCBlocks.decay_generator, "decay_generator", RecipesJEI.DecayGenerator.class),
		FISSION(NCRecipes.Type.FISSION, NCBlocks.fission_controller_new_idle, "fission_controller", RecipesJEI.Fission.class),
		FUSION(NCRecipes.Type.FUSION, NCBlocks.fusion_core, "fusion_core", RecipesJEI.Fusion.class),
		SALT_FISSION(NCRecipes.Type.SALT_FISSION, NCBlocks.salt_fission_vessel, "salt_fission", RecipesJEI.SaltFission.class),
		COOLANT_HEATER(NCRecipes.Type.COOLANT_HEATER, NCBlocks.salt_fission_heater, "coolant_heater", RecipesJEI.CoolantHeater.class);
		
		public NCRecipes.Type recipeType;
		public String unlocalizedName;
		public String textureName;
		public Class<? extends JEIRecipe> recipeClass;
		public ItemStack crafterType;
		
		Handlers(NCRecipes.Type recipeType, Object crafter, String textureName, Class<? extends JEIRecipe> recipeClass) {
			this.recipeType = recipeType;
			crafterType = crafter != null ? ItemStackHelper.fixItemStack(crafter) : null;
			this.unlocalizedName = crafterType != null ? crafterType.getUnlocalizedName() + ".name" : "";
			this.textureName = textureName;
			this.recipeClass = recipeClass;
		}
		
		@Override
		public JEICategory getCategory(IGuiHelper guiHelper) {
			switch (this) {
			case MANUFACTORY:
				return new ManufactoryCategory(guiHelper, this);
			case ISOTOPE_SEPARATOR:
				return new IsotopeSeparatorCategory(guiHelper, this);
			case DECAY_HASTENER:
				return new DecayHastenerCategory(guiHelper, this);
			case FUEL_REPROCESSOR:
				return new FuelReprocessorCategory(guiHelper, this);
			case ALLOY_FURNACE:
				return new AlloyFurnaceCategory(guiHelper, this);
			case INFUSER:
				return new InfuserCategory(guiHelper, this);
			case MELTER:
				return new MelterCategory(guiHelper, this);
			case SUPERCOOLER:
				return new SupercoolerCategory(guiHelper, this);
			case ELECTROLYSER:
				return new ElectrolyserCategory(guiHelper, this);
			case IRRADIATOR:
				return new IrradiatorCategory(guiHelper, this);
			case INGOT_FORMER:
				return new IngotFormerCategory(guiHelper, this);
			case PRESSURIZER:
				return new PressurizerCategory(guiHelper, this);
			case CHEMICAL_REACTOR:
				return new ChemicalReactorCategory(guiHelper, this);
			case SALT_MIXER:
				return new SaltMixerCategory(guiHelper, this);
			case CRYSTALLIZER:
				return new CrystallizerCategory(guiHelper, this);
			case DISSOLVER:
				return new DissolverCategory(guiHelper, this);
			case EXTRACTOR:
				return new ExtractorCategory(guiHelper, this);
			case CENTRIFUGE:
				return new CentrifugeCategory(guiHelper, this);
			case ROCK_CRUSHER:
				return new RockCrusherCategory(guiHelper, this);
			case DECAY_GENERATOR:
				return new DecayGeneratorCategory(guiHelper, this);
			case FISSION:
				return new FissionCategory(guiHelper, this);
			case FUSION:
				return new FusionCategory(guiHelper, this);
			case SALT_FISSION:
				return new SaltFissionCategory(guiHelper, this);
			case COOLANT_HEATER:
				return new CoolantHeaterCategory(guiHelper, this);
			default:
				return null;
			}
		}
		
		@Override
		public String getTextureName() {
			return textureName;
		}
		
		@Override
		public String getTitle() {
			return unlocalizedName;
		}
		
		@Override
		public Class<? extends JEIRecipe> getRecipeClass() {
			return recipeClass;
		}

		@Override
		public BaseRecipeHandler getRecipeHandler() {
			return recipeType.getRecipeHandler();
		}

		@Override
		public ArrayList<JEIRecipe> getJEIRecipes() {
			ArrayList<JEIRecipe> recipes = new ArrayList();
			if (getRecipeHandler() != null) {
				for (IRecipe recipe : (ArrayList<IRecipe>) getRecipeHandler().getRecipes()) {
					try {
						recipes.add(recipeClass.getConstructor(BaseRecipeHandler.class, IRecipe.class).newInstance(getRecipeHandler(), recipe));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			return recipes;
		}

		@Override
		public ItemStack getCrafterItemStack() {
			return crafterType;
		}
		
		@Override
		public String getUUID() {
			return getRecipeHandler().getRecipeName();
		}
	}
}
