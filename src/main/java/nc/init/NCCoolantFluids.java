package nc.init;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import nc.NuclearCraft;
import nc.block.fluid.BlockFluidBase;
import nc.block.fluid.BlockFluidCoolant;
import nc.block.fluid.BlockFluidCryotheum;
import nc.block.fluid.BlockFluidGlowstone;
import nc.block.fluid.BlockFluidHotCoolant;
import nc.block.fluid.BlockFluidMolten;
import nc.config.NCConfig;
import nc.fluid.FluidCoolant;
import nc.fluid.FluidCryotheum;
import nc.fluid.FluidGlowstone;
import nc.fluid.FluidHotCoolant;
import nc.fluid.FluidMolten;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class NCCoolantFluids {
	
	public static List<Pair<Fluid, BlockFluidBase>> fluidPairList = new ArrayList<Pair<Fluid, BlockFluidBase>>();
	public static List<Fluid> fluidList = new ArrayList<Fluid>();
	
	static {
		if (NCConfig.register_cofh_fluids) {
			FluidMolten redstone = new FluidMolten("redstone", 0xAB1C09);
			fluidPairList.add(Pair.of(redstone, new BlockFluidMolten(redstone)));
		}
		
		FluidCoolant redstone_nak = new FluidCoolant("redstone_nak", FluidCoolant.getNAKColor(0xAB1C09));
		FluidHotCoolant redstone_nak_hot = new FluidHotCoolant("redstone_nak_hot", FluidHotCoolant.getNAKColor(0xAB1C09));
		fluidPairList.add(Pair.of(redstone_nak, new BlockFluidCoolant(redstone_nak)));
		fluidPairList.add(Pair.of(redstone_nak_hot, new BlockFluidHotCoolant(redstone_nak_hot)));
		
		FluidMolten quartz = new FluidMolten("quartz", 0xECE9E2);
		fluidPairList.add(Pair.of(quartz, new BlockFluidMolten(quartz)));
		
		FluidCoolant quartz_nak = new FluidCoolant("quartz_nak", FluidCoolant.getNAKColor(0xECE9E2));
		FluidHotCoolant quartz_nak_hot = new FluidHotCoolant("quartz_nak_hot", FluidHotCoolant.getNAKColor(0xECE9E2));
		fluidPairList.add(Pair.of(quartz_nak, new BlockFluidCoolant(quartz_nak)));
		fluidPairList.add(Pair.of(quartz_nak_hot, new BlockFluidHotCoolant(quartz_nak_hot)));
		
		FluidMolten gold = new FluidMolten("gold", 0xE6DA3C);
		fluidPairList.add(Pair.of(gold, new BlockFluidMolten(gold)));
		
		FluidCoolant gold_nak = new FluidCoolant("gold_nak", FluidCoolant.getNAKColor(0xE6DA3C));
		FluidHotCoolant gold_nak_hot = new FluidHotCoolant("gold_nak_hot", FluidHotCoolant.getNAKColor(0xE6DA3C));
		fluidPairList.add(Pair.of(gold_nak, new BlockFluidCoolant(gold_nak)));
		fluidPairList.add(Pair.of(gold_nak_hot, new BlockFluidHotCoolant(gold_nak_hot)));
		
		if (NCConfig.register_cofh_fluids) {
			FluidGlowstone glowstone = new FluidGlowstone("glowstone", 0xA38037);
			fluidPairList.add(Pair.of(glowstone, new BlockFluidGlowstone(glowstone)));
		}
		
		FluidCoolant glowstone_nak = new FluidCoolant("glowstone_nak", FluidCoolant.getNAKColor(0xA38037));
		FluidHotCoolant glowstone_nak_hot = new FluidHotCoolant("glowstone_nak_hot", FluidHotCoolant.getNAKColor(0xA38037));
		fluidPairList.add(Pair.of(glowstone_nak, new BlockFluidCoolant(glowstone_nak)));
		fluidPairList.add(Pair.of(glowstone_nak_hot, new BlockFluidHotCoolant(glowstone_nak_hot)));
		
		FluidMolten lapis = new FluidMolten("lapis", 0x27438A);
		fluidPairList.add(Pair.of(lapis, new BlockFluidMolten(lapis)));
		
		FluidCoolant lapis_nak = new FluidCoolant("lapis_nak", FluidCoolant.getNAKColor(0x27438A));
		FluidHotCoolant lapis_nak_hot = new FluidHotCoolant("lapis_nak_hot", FluidHotCoolant.getNAKColor(0x27438A));
		fluidPairList.add(Pair.of(lapis_nak, new BlockFluidCoolant(lapis_nak)));
		fluidPairList.add(Pair.of(lapis_nak_hot, new BlockFluidHotCoolant(lapis_nak_hot)));
		
		FluidMolten diamond = new FluidMolten("diamond", 0x6FDFDA);
		fluidPairList.add(Pair.of(diamond, new BlockFluidMolten(diamond)));
		
		FluidCoolant diamond_nak = new FluidCoolant("diamond_nak", FluidCoolant.getNAKColor(0x6FDFDA));
		FluidHotCoolant diamond_nak_hot = new FluidHotCoolant("diamond_nak_hot", FluidHotCoolant.getNAKColor(0x6FDFDA));
		fluidPairList.add(Pair.of(diamond_nak, new BlockFluidCoolant(diamond_nak)));
		fluidPairList.add(Pair.of(diamond_nak_hot, new BlockFluidHotCoolant(diamond_nak_hot)));
		
		FluidCoolant liquidhelium_nak = new FluidCoolant("liquidhelium_nak", FluidCoolant.getNAKColor(0xF3433D));
		FluidHotCoolant liquidhelium_nak_hot = new FluidHotCoolant("liquidhelium_nak_hot", FluidHotCoolant.getNAKColor(0xF3433D));
		fluidPairList.add(Pair.of(liquidhelium_nak, new BlockFluidCoolant(liquidhelium_nak)));
		fluidPairList.add(Pair.of(liquidhelium_nak_hot, new BlockFluidHotCoolant(liquidhelium_nak_hot)));
		
		if (NCConfig.register_cofh_fluids) {
			FluidMolten ender = new FluidMolten("ender", 0x14584D);
			fluidPairList.add(Pair.of(ender, new BlockFluidMolten(ender)));
		}
		
		FluidCoolant ender_nak = new FluidCoolant("ender_nak", FluidCoolant.getNAKColor(0x14584D));
		FluidHotCoolant ender_nak_hot = new FluidHotCoolant("ender_nak_hot", FluidHotCoolant.getNAKColor(0x14584D));
		fluidPairList.add(Pair.of(ender_nak, new BlockFluidCoolant(ender_nak)));
		fluidPairList.add(Pair.of(ender_nak_hot, new BlockFluidHotCoolant(ender_nak_hot)));
		
		if (NCConfig.register_cofh_fluids) {
			FluidCryotheum cryotheum = new FluidCryotheum("cryotheum", 0x0099C1);
			fluidPairList.add(Pair.of(cryotheum, new BlockFluidCryotheum(cryotheum)));
		}
		
		FluidCoolant cryotheum_nak = new FluidCoolant("cryotheum_nak", FluidCoolant.getNAKColor(0x0099C1));
		FluidHotCoolant cryotheum_nak_hot = new FluidHotCoolant("cryotheum_nak_hot", FluidHotCoolant.getNAKColor(0x0099C1));
		fluidPairList.add(Pair.of(cryotheum_nak, new BlockFluidCoolant(cryotheum_nak)));
		fluidPairList.add(Pair.of(cryotheum_nak_hot, new BlockFluidHotCoolant(cryotheum_nak_hot)));
		
		FluidMolten iron = new FluidMolten("iron", 0x8D1515);
		fluidPairList.add(Pair.of(iron, new BlockFluidMolten(iron)));
		
		FluidCoolant iron_nak = new FluidCoolant("iron_nak", FluidCoolant.getNAKColor(0x8D1515));
		FluidHotCoolant iron_nak_hot = new FluidHotCoolant("iron_nak_hot", FluidHotCoolant.getNAKColor(0x8D1515));
		fluidPairList.add(Pair.of(iron_nak, new BlockFluidCoolant(iron_nak)));
		fluidPairList.add(Pair.of(iron_nak_hot, new BlockFluidHotCoolant(iron_nak_hot)));
		
		FluidMolten emerald = new FluidMolten("emerald", 0x51D975);
		fluidPairList.add(Pair.of(emerald, new BlockFluidMolten(emerald)));
		
		FluidCoolant emerald_nak = new FluidCoolant("emerald_nak", FluidCoolant.getNAKColor(0x51D975));
		FluidHotCoolant emerald_nak_hot = new FluidHotCoolant("emerald_nak_hot", FluidHotCoolant.getNAKColor(0x51D975));
		fluidPairList.add(Pair.of(emerald_nak, new BlockFluidCoolant(emerald_nak)));
		fluidPairList.add(Pair.of(emerald_nak_hot, new BlockFluidHotCoolant(emerald_nak_hot)));
		
		FluidMolten copper = new FluidMolten("copper", 0x5C2F1A);
		fluidPairList.add(Pair.of(copper, new BlockFluidMolten(copper)));
		
		FluidCoolant copper_nak = new FluidCoolant("copper_nak", FluidCoolant.getNAKColor(0x5C2F1A));
		FluidHotCoolant copper_nak_hot = new FluidHotCoolant("copper_nak_hot", FluidHotCoolant.getNAKColor(0x5C2F1A));
		fluidPairList.add(Pair.of(copper_nak, new BlockFluidCoolant(copper_nak)));
		fluidPairList.add(Pair.of(copper_nak_hot, new BlockFluidHotCoolant(copper_nak_hot)));
		
		FluidMolten tin = new FluidMolten("tin", 0xD9DDF0);
		fluidPairList.add(Pair.of(tin, new BlockFluidMolten(tin)));
		
		FluidCoolant tin_nak = new FluidCoolant("tin_nak", FluidCoolant.getNAKColor(0xD9DDF0));
		FluidHotCoolant tin_nak_hot = new FluidHotCoolant("tin_nak_hot", FluidHotCoolant.getNAKColor(0xD9DDF0));
		fluidPairList.add(Pair.of(tin_nak, new BlockFluidCoolant(tin_nak)));
		fluidPairList.add(Pair.of(tin_nak_hot, new BlockFluidHotCoolant(tin_nak_hot)));
		
		FluidMolten magnesium = new FluidMolten("magnesium", 0xEED5E1);
		fluidPairList.add(Pair.of(magnesium, new BlockFluidMolten(magnesium)));
		
		FluidCoolant magnesium_nak = new FluidCoolant("magnesium_nak", FluidCoolant.getNAKColor(0xEED5E1));
		FluidHotCoolant magnesium_nak_hot = new FluidHotCoolant("magnesium_nak_hot", FluidHotCoolant.getNAKColor(0xEED5E1));
		fluidPairList.add(Pair.of(magnesium_nak, new BlockFluidCoolant(magnesium_nak)));
		fluidPairList.add(Pair.of(magnesium_nak_hot, new BlockFluidHotCoolant(magnesium_nak_hot)));
	}
	
	public static void register() {
		for (Pair<Fluid, BlockFluidBase> fluidPair : fluidPairList) {
			Fluid fluid = fluidPair.getLeft();
			
			boolean defaultFluid = FluidRegistry.registerFluid(fluid);
			if (!defaultFluid) fluid = FluidRegistry.getFluid(fluid.getName());
			FluidRegistry.addBucketForFluid(fluid);
			fluidList.add(fluid);
			
			if (NCConfig.register_fission_fluid_blocks) registerBlock(fluidPair.getRight());
		}
	}
	
	public static void registerBlock(BlockFluidBase block) {
		ForgeRegistries.BLOCKS.register(block);
		ForgeRegistries.ITEMS.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
		NuclearCraft.proxy.registerFluidBlockRendering(block, "fluid_molten_colored");
	}
}
