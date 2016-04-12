package stellarium.stellars.system;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.config.Configuration;
import stellarium.config.INBTConfig;
import stellarium.config.SimpleNBTConfig;
import stellarium.config.property.ConfigPropertyDouble;

public class SolarSystemSettings extends SimpleNBTConfig {
		
	public ConfigPropertyDouble propMoonSize, propMoonBrightness;
	
	public SolarSystemSettings() {
		this.propMoonSize = new ConfigPropertyDouble("Moon_Size", "moonSize", 1.0);
		this.propMoonBrightness = new ConfigPropertyDouble("Moon_Brightness", "moonBrightness", 1.0);
		
		this.addConfigProperty(this.propMoonSize);
		this.addConfigProperty(this.propMoonBrightness);
	}

	@Override
	public void setupConfig(Configuration config, String category) {
		config.setCategoryComment(category, "Configurations for solar system.");
		config.setCategoryLanguageKey(category, "config.category.solarsystem");
		config.setCategoryRequiresWorldRestart(category, true);
		
		super.setupConfig(config, category);
		
       	propMoonSize.setComment("Size of moon. (Default size is 1.0)");
       	propMoonSize.setRequiresWorldRestart(true);
       	propMoonSize.setLanguageKey("config.property.server.moonsize");
        
       	propMoonBrightness.setComment("Brightness of moon. (Default brightness is 1.0)");
       	propMoonBrightness.setRequiresWorldRestart(true);
       	propMoonBrightness.setLanguageKey("config.property.server.moonbrightness");
	}

	@Override
	public INBTConfig copy() {
		SolarSystemSettings settings = new SolarSystemSettings();
		super.applyCopy(settings);
		return settings;
	}

}
