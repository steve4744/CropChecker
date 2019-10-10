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
package io.github.steve4744.cropchecker.data;

import org.bukkit.Material;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Levelled;
import org.bukkit.block.data.type.TurtleEgg;
import org.bukkit.configuration.file.FileConfiguration;

import io.github.steve4744.cropchecker.CropChecker;

public class DataHandler {

	private String text;
	private FileConfiguration cfg;

	public DataHandler(CropChecker plugin) {
		cfg = plugin.getConfiguration().getStringData();
	}

	public String getDisplayName(Material crop) {
		String path = "crops.";
		String cropname = crop.name().toLowerCase();
		if (cropname.equalsIgnoreCase("composter") || cropname.equalsIgnoreCase("cauldron") || cropname.equalsIgnoreCase("turtle_egg")) {
			path = "item.";
		}
		return cfg.getString(path + cropname, crop.name());	
	}

	private String getGrowthText() {
		return cfg.getString("text.growth", "Growth");	
	}

	private String getLevelText() {
		return cfg.getString("text.level", "Level");
	}

	public String getText() {
		return text;
	}

	public String getPadding(Material crop) {
		String cropname = getDisplayName(crop);
		
		int padlen = 5;
		//we need extra padding if crop name is shorter than the text
		if (cropname.length() < getText().length()) {
			int mismatch = getText().length() - cropname.length();
			//pad with extra 10 spaces for good measure
			padlen += mismatch + 10;
		}
		return String.format("%" + padlen + "s", "%");
	}

	public int getProgress(BlockData bdata) {
		int progress = 0;

		if (bdata instanceof Ageable) {
			Ageable age = (Ageable) bdata;
			progress = age.getAge() * 100 / age.getMaximumAge();
			text = getGrowthText();

		} else if (bdata instanceof Levelled) {
			Levelled levelled = (Levelled) bdata;
			progress = levelled.getLevel() * 100 / levelled.getMaximumLevel();
			text = getLevelText();

		} else if (bdata instanceof TurtleEgg) {
			TurtleEgg turtleEgg = (TurtleEgg) bdata;
			progress = turtleEgg.getHatch() *100 / turtleEgg.getMaximumHatch();
			text = getGrowthText();
		}

		return progress;
	}

}
