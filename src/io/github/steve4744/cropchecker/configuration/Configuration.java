/*
 * MIT License

Copyright (c) 2019 steve4744

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

 */
package io.github.steve4744.cropchecker.configuration;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.google.common.base.Enums;

import io.github.steve4744.cropchecker.CropChecker;

public class Configuration {

	private File stringFile, dataFolder;
	private FileConfiguration stringData;
	private final CropChecker plugin;

	public Configuration(CropChecker plugin) {

		this.plugin = plugin;
		dataFolder = plugin.getDataFolder();
		stringFile = new File(dataFolder, "strings.yml");
		stringData = new YamlConfiguration();

		if (!stringFile.exists()) {
			try {
				stringFile.createNewFile();
				plugin.getLogger().info("Created strings.yml");
			} catch (Exception ex) {
				ex.printStackTrace();
				plugin.getLogger().info("Failed!");
			}
		}
		reloadStrings();
		saveStrings();
	}

	public void reloadStrings() {
		try {
			stringData.load(stringFile);

		} catch (Exception ex) {
			plugin.getLogger().info("Failed loading config: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public FileConfiguration getStringData() {
		return stringData;
    }

	public void saveStrings() {
		try{
			stringData.addDefault("crops.wheat", "Wheat");
			stringData.addDefault("crops.carrots", "Carrots");
			stringData.addDefault("crops.potatoes", "Potatoes");
			stringData.addDefault("crops.beetroots", "Beetroots");
			stringData.addDefault("crops.melon_stem", "Melon_Stem");
			stringData.addDefault("crops.pumpkin_stem", "Pumpkin_Stem");
			stringData.addDefault("crops.kelp", "Kelp");
			stringData.addDefault("crops.cocoa", "Cocoa");
			stringData.addDefault("crops.bamboo", "Bamboo");
			stringData.addDefault("crops.cactus", "Cactus");
			stringData.addDefault("crops.sugar_cane", "Sugar_Cane");
			stringData.addDefault("crops.nether_wart", "Nether_Wart");
			stringData.addDefault("crops.chorus_flower", "Chorus_Flower");
			stringData.addDefault("crops.sweet_berry_bush", "Sweet_Berry_Bush");
			stringData.addDefault("crops.acacia_sapling", "Acacia_Sapling");
			stringData.addDefault("crops.birch_sapling", "Birch_Sapling");
			stringData.addDefault("crops.dark_oak_sapling", "Dark_Oak_Sapling");
			stringData.addDefault("crops.jungle_sapling", "Jungle_Sapling");
			stringData.addDefault("crops.oak_sapling", "Oak_Sapling");
			stringData.addDefault("crops.spruce_sapling", "Spruce_Sapling");
			stringData.addDefault("crops.mangrove_propagule", "Mangrove_Propagule");
			stringData.addDefault("crops.twisting_vines", "Twisting_Vines");
			stringData.addDefault("crops.weeping_vines", "Weeping_Vines");
			stringData.addDefault("crops.cave_vines", "Cave_Vines");
			stringData.addDefault("item.water_cauldron", "Water_Cauldron");
			stringData.addDefault("item.powder_snow_cauldron", "Powder_Snow_Cauldron");
			stringData.addDefault("item.composter", "Composter");
			stringData.addDefault("item.turtle_egg", "Turtle_Egg");
			stringData.addDefault("item.bee_nest", "Bee_Nest");
			stringData.addDefault("item.beehive", "Beehive");
			stringData.addDefault("text.level", "Level");
			stringData.addDefault("text.growth", "Growth");
			stringData.options().copyDefaults(true);
			stringData.save(stringFile);
			reloadStrings();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public boolean isScoreboardEnabled() {
		return plugin.getConfig().getBoolean("Display.scoreboard.enabled", true);
	}

	public boolean isActionBarEnabled() {
		return plugin.getConfig().getBoolean("Display.actionbar.enabled", true);
	}

	public boolean isBossbarEnabled() {
		return plugin.getConfig().getBoolean("Display.bossbar.enabled", true);
	}

	public String getActionBarColor() {
		String colour = plugin.getConfig().getString("Display.actionbar.textcolor").toUpperCase();
		if (colour == null || Enums.getIfPresent(ChatColor.class, colour).orNull() == null) {
			colour = "WHITE";
		}
		return colour;
	}

	public String getBossbarColor() {
		String colour = plugin.getConfig().getString("Display.bossbar.barcolor").toUpperCase();
		if (colour == null || Enums.getIfPresent(BarColor.class, colour).orNull() == null) {
			colour = "GREEN";
		}
		return colour;
	}

	public String getBossbarTextColor() {
		String colour = plugin.getConfig().getString("Display.bossbar.textcolor").toUpperCase();
		if (colour == null || Enums.getIfPresent(BarColor.class, colour).orNull() == null) {
			colour = "WHITE";
		}
		return colour;
	}

}
