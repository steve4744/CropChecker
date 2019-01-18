package io.github.steve4744.cropchecker.configuration;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import io.github.steve4744.cropchecker.CropChecker;

public class Configuration {
	
	private File stringFile, dataFolder;
	private static FileConfiguration stringData;

	public Configuration(CropChecker plugin) {

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

	public static FileConfiguration getStringData() {
		return stringData;
    }

	public void saveStrings() {
		try{
			stringData.addDefault("crops.wheat", "WHEAT");
			stringData.addDefault("crops.carrots", "CARROTS");
			stringData.addDefault("crops.potatoes", "POTATOES");
			stringData.addDefault("crops.beetroots", "BEETROOTS");
			stringData.addDefault("crops.melon_stem", "MELON_STEM");
			stringData.addDefault("crops.pumpkin_stem", "PUMPKIN_STEM");
			stringData.addDefault("crops.kelp", "KELP");
			stringData.addDefault("crops.cocoa", "COCOA");
			stringData.addDefault("crops.cactus", "CACTUS");
			stringData.addDefault("crops.sugar_cane", "SUGAR_CANE");
			stringData.addDefault("crops.nether_wart", "NETHER_WART");
			stringData.addDefault("crops.chorus_flower", "CHORUS_FLOWER");
			stringData.addDefault("text.growth", "Growth");
			stringData.options().copyDefaults(true);
			stringData.save(stringFile);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
