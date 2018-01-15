package stellarium.world;

import net.minecraft.world.World;
import stellarapi.api.ICelestialPack;
import stellarapi.api.ICelestialScene;
import stellarapi.api.lib.config.INBTConfig;
import stellarapi.api.world.worldset.WorldSet;
import stellarapi.impl.celestial.DefaultCelestialPack;
import stellarium.StellarSky;

public enum StellarPack implements ICelestialPack {
	INSTANCE;

	@Override
	public String getPackName() {
		return "Stellar Sky";
	}

	@Override
	public ICelestialScene getScene(WorldSet worldSet, World world, boolean isDefault) {
		if(isDefault) {
			
		} else {
			PerDimensionSettings settings = (PerDimensionSettings) ((INBTConfig) StellarSky.PROXY.getDimensionSettings().getSubConfig(worldSet.name)).copy();
		}

		// TODO AA Stellar API side - fix the vanillaServer parameter for server default - clinent non-default case
		// Or use detecting the server pack.
		return new StellarScene(world, worldSet);
	}

}
