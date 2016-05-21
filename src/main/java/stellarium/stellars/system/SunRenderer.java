package stellarium.stellars.system;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import stellarapi.api.lib.math.Vector3;
import stellarium.StellarSkyResources;
import stellarium.render.EnumRenderPass;
import stellarium.render.ICelestialObjectRenderer;
import stellarium.render.StellarRenderInfo;

public class SunRenderer implements ICelestialObjectRenderer<SunRenderCache> {
	
	@Override
	public void render(StellarRenderInfo info, SunRenderCache cache) {
		if(info.pass != EnumRenderPass.ShallowScattering)
			return;
		
		Vector3 pos = cache.pos;
		Vector3 dif = cache.dif;
		Vector3 dif2 = cache.dif2;
		
		GlStateManager.color(1.0f, 1.0f, 1.0f, info.weathereff);
		
		info.mc.renderEngine.bindTexture(StellarSkyResources.resourceSunHalo.getLocation());
		info.worldrenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		info.worldrenderer.pos(pos.getX()+dif.getX(),  pos.getY()+dif.getY(),  pos.getZ()+dif.getZ()).tex(0.0,0.0).endVertex();
		info.worldrenderer.pos(pos.getX()+dif2.getX(), pos.getY()+dif2.getY(), pos.getZ()+dif2.getZ()).tex(1.0,0.0).endVertex();
		info.worldrenderer.pos(pos.getX()-dif.getX(),  pos.getY()-dif.getY(),  pos.getZ()-dif.getZ()).tex(1.0,1.0).endVertex();
		info.worldrenderer.pos(pos.getX()-dif2.getX(), pos.getY()-dif2.getY(), pos.getZ()-dif2.getZ()).tex(0.0,1.0).endVertex();
		info.tessellator.draw();
	}

}
