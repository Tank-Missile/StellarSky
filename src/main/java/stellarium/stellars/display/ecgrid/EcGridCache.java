package stellarium.stellars.display.ecgrid;

import stellarapi.api.lib.math.SpCoord;
import stellarapi.api.lib.math.Vector3;
import stellarium.client.ClientSettings;
import stellarium.render.EnumRenderPass;
import stellarium.stellars.display.IDisplayRenderCache;
import stellarium.stellars.layer.StellarCacheInfo;
import stellarium.util.math.VectorHelper;

public class EcGridCache implements IDisplayRenderCache<EcGridSettings> {
	
	private Vector3 baseColor, latitudeColor, longitudeColor;
	protected Vector3[][] displayvec = null;
	protected Vector3[][] colorvec = null;
	protected Vector3[] ecliptic = null;
	protected int latn, longn;
	protected boolean enabled;
	protected float brightness;
	
	@Override
	public void initialize(ClientSettings settings, EcGridSettings specificSettings) {
		this.latn = specificSettings.displayFrag;
		this.longn = 2*specificSettings.displayFrag;
		this.enabled = specificSettings.displayEnabled;
		if(this.enabled) {
			this.displayvec = VectorHelper.createAndInitialize(longn, latn+1);
			this.colorvec = VectorHelper.createAndInitialize(longn, latn+1);
			this.ecliptic = VectorHelper.createAndInitialize(longn);
		}
		this.brightness = (float) specificSettings.displayAlpha;
		this.baseColor = new Vector3(specificSettings.displayBaseColor);
		this.latitudeColor = new Vector3(specificSettings.displayHeightColor);
		this.longitudeColor = new Vector3(specificSettings.displayAzimuthColor);
		latitudeColor.sub(this.baseColor);
		longitudeColor.sub(this.baseColor);
	}

	@Override
	public void updateCache(ClientSettings settings, EcGridSettings specificSettings, StellarCacheInfo info) {
		if(!this.enabled)
			return;
		
		for(int longc=0; longc<longn; longc++){
			Vector3 Buf = new SpCoord(-longc*360.0/longn, 0.0).getVec();
			info.projectionToGround.transform(Buf);
			
			SpCoord coord = new SpCoord();
			coord.setWithVec(Buf);
			
			info.applyAtmRefraction(coord);
			
			ecliptic[longc].set(coord.getVec());
			ecliptic[longc].scale(EnumRenderPass.DEEP_DEPTH + 1.0);
			
			for(int latc=0; latc<=latn; latc++){
				Buf = new SpCoord(longc*360.0/longn, latc*180.0/latn - 90.0).getVec();
				info.projectionToGround.transform(Buf);
				
				coord = new SpCoord();
				coord.setWithVec(Buf);
				
				info.applyAtmRefraction(coord);

				displayvec[longc][latc].set(coord.getVec());
				displayvec[longc][latc].scale(EnumRenderPass.DEEP_DEPTH + 2.0);
				
				colorvec[longc][latc].set(this.baseColor);
				
				Buf.set(this.latitudeColor);
				Buf.scale((double)latc/latn);
				colorvec[longc][latc].add(Buf);
				
				Buf.set(this.longitudeColor);
				Buf.scale((double)longc/longn);
				colorvec[longc][latc].add(Buf);
			}
		}
	}

}