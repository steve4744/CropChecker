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
				saveStrings();
			} catch (Exception ex) {
				ex.printStackTrace();
				plugin.getLogger().info("Failed!");
			}
		}
		try {
			stringData.load(stringFile);

		} catch (Exception ex) {
			plugin.getLogger().info("Failed loading config: " + ex.getMessage());
			ex.printStackTrace();
		}
		saveStrings();
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
			stringData.addDefault("item.cauldron", "Cauldron");
			stringData.addDefault("item.composter", "Composter");
			stringData.addDefault("text.level", "Level");
			stringData.addDefault("text.growth", "Growth");
			stringData.options().copyDefaults(true);
			stringData.save(stringFile);
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

	public String getActionBarColor() {
		String colour = plugin.getConfig().getString("Display.actionbar.textcolor").toUpperCase();
		if (colour == null || Enums.getIfPresent(ChatColor.class, colour).orNull() == null) {
			colour = "WHITE";
		}
		return colour;
	}

}
