package stellarium.stellars.system;

import stellarium.render.stellars.access.EnumStellarPass;
import stellarium.render.stellars.layer.LayerRHelper;
import stellarium.stellars.OpticsHelper;
import stellarium.stellars.render.ICelestialObjectRenderer;

public enum PlanetRenderer implements ICelestialObjectRenderer<PlanetRenderCache> {

	INSTANCE;

	@Override
	public void render(PlanetRenderCache cache, EnumStellarPass pass, LayerRHelper info) {
		if(pass != EnumStellarPass.Source || !cache.shouldRender)
			return;

		// TODO Render fuzzy shape, for now it just render points
		float multiplier = OpticsHelper.getMultFromArea(info.pointArea());

		info.beginPoint();
		info.renderPoint(cache.pos,
				cache.brightness * multiplier, cache.brightness * multiplier, cache.brightness * multiplier);
		info.endPoint();
	}

}
