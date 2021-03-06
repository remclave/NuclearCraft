package nc.recipe.processor;

import nc.config.NCConfig;
import nc.recipe.BaseRecipeHandler;
import nc.util.FluidHelper;

public class IrradiatorRecipes extends BaseRecipeHandler {
	
	public IrradiatorRecipes() {
		super("irradiator", 0, 2, 0, 2);
	}

	@Override
	public void addRecipes() {
		//addRecipe(fluidStack("helium3", FluidHelper.BUCKET_VOLUME), fluidStack("neutron", FluidHelper.PARTICLE_VOLUME), fluidStack("hydrogen", FluidHelper.BUCKET_VOLUME), fluidStack("tritium", FluidHelper.BUCKET_VOLUME), NCConfig.processor_time[9]);
		addRecipe(fluidStack("lithium6", FluidHelper.INGOT_VOLUME), fluidStack("neutron", FluidHelper.PARTICLE_VOLUME), fluidStack("tritium", FluidHelper.BUCKET_VOLUME), fluidStack("helium", FluidHelper.BUCKET_VOLUME), NCConfig.processor_time[9]);
		addRecipe(fluidStack("boron10", FluidHelper.INGOT_VOLUME), fluidStack("neutron", FluidHelper.PARTICLE_VOLUME), fluidStack("tritium", FluidHelper.BUCKET_VOLUME), fluidStack("helium", FluidHelper.BUCKET_VOLUME*2), NCConfig.processor_time[9]);
	}
}
