package stellarium.stellars.display;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraftforge.common.config.Configuration;
import stellarapi.api.lib.config.IConfigHandler;
import stellarium.stellars.display.eccoord.DisplayEcCoord;
import stellarium.stellars.display.eqcoord.DisplayEqCoord;
import stellarium.stellars.display.horcoord.DisplayHorCoord;

public class DisplaySettings implements IConfigHandler {
	
	private List<DisplayElement> elements = Lists.newArrayList();
	
	public DisplaySettings() {
		elements.add(new DisplayHorCoord());
		elements.add(new DisplayEqCoord());
		elements.add(new DisplayEcCoord());
	}

	@Override
	public void setupConfig(Configuration config, String category) {
		config.setCategoryComment(category, "Configurations for additional display.");
		config.setCategoryLanguageKey(category, "config.category.display");
		config.setCategoryRequiresMcRestart(category, false);
		
		for(DisplayElement element : elements)
			element.setupConfig(config, category + Configuration.CATEGORY_SPLITTER + element.getID());
	}

	@Override
	public void loadFromConfig(Configuration config, String category) {
		for(DisplayElement element : elements)
			element.loadFromConfig(config, category + Configuration.CATEGORY_SPLITTER + element.getID());
	}

	@Override
	public void saveToConfig(Configuration config, String category) {
		for(DisplayElement element : elements)
			element.saveToConfig(config, category + Configuration.CATEGORY_SPLITTER + element.getID());
	}

	protected List<DisplayElement> getDisplayElements() {
		return this.elements;
	}

}