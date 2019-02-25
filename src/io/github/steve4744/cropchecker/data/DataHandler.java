package io.github.steve4744.cropchecker.data;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import io.github.steve4744.cropchecker.CropChecker;
import io.github.steve4744.cropchecker.configuration.Configuration;

public class DataHandler {

	private final CropChecker plugin;

	public DataHandler(CropChecker plugin) {
		this.plugin = plugin;
	}

	public String getDisplayName(Material crop) {
		FileConfiguration cfg = Configuration.getStringData();
		String cropname = crop.name().toLowerCase();
		return cfg.getString("crops." + cropname, crop.name());	
	}

	public String getText() {
		FileConfiguration cfg = Configuration.getStringData();
		return cfg.getString("text.growth", "Growth");	
	}

	public String getPadding(Material crop) {
		StringBuilder pad = new StringBuilder();
		int padlen = 5;
		String cropname = getDisplayName(crop);

		//we need extra padding if crop name is shorter than the text
		if (cropname.length() < getText().length()) {
			int mismatch = getText().length() - cropname.length();
			if (mismatch >= 6) {
				padlen = 12;
			} else if (mismatch >= 4) {
				padlen = 8;
			} 
		}
		for (int i = 0; i < padlen; i++) {
			pad.append(" ");
		}
		return pad.toString();
	}
}
