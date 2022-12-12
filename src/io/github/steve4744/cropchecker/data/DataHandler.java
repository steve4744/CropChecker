/*
 * MIT License

Copyright (c) 2022 steve4744

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

import java.util.stream.Stream;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Levelled;
import org.bukkit.block.data.type.Beehive;
import org.bukkit.block.data.type.CaveVinesPlant;
import org.bukkit.block.data.type.Sapling;
import org.bukkit.block.data.type.TurtleEgg;
import org.bukkit.configuration.file.FileConfiguration;

import dev.lone.itemsadder.api.CustomBlock;
import dev.lone.itemsadder.api.CustomCrop;
import io.github.steve4744.cropchecker.CropChecker;

public class DataHandler {

	private String text;
	private FileConfiguration cfg;
	private CropChecker plugin;

	public DataHandler(CropChecker plugin) {
		cfg = plugin.getConfiguration().getStringData();
		this.plugin = plugin;
	}

	/**
	 * Get the localised name to use as the name to display.
	 *
	 * @param block
	 * @return string display name
	 */
	public String getDisplayName(Block block) {
		if (plugin.isItemsAdder() && CustomCrop.byAlreadyPlaced(block) != null) {
			return CustomBlock.byAlreadyPlaced(block).getDisplayName();
		}
		String cropname = block.getType().toString().toLowerCase();

		String path = Stream.of("composter", "water_cauldron", "powder_snow_cauldron", "turtle_egg", "beehive", "bee_nest")
							.anyMatch(s -> cropname.equalsIgnoreCase(s)) ? "item." : "crops.";

		return cfg.getString(path + cropname, cropname);
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

	/**
	 * Attempt to pad the display string for the scoreboard if the crop name is shorter than the text,
	 * so that the % sign is in the last char position.
	 *
	 * @param display name
	 * @return display name with trailing spaces added
	 */
	public String getPadding(String displayName) {
		int padlen = 5;

		if (displayName.length() < getText().length()) {
			int mismatch = getText().length() - displayName.length();
			//pad with extra 10 spaces for good measure
			padlen += mismatch + 10;
		}
		return String.format("%" + padlen + "s", "%");
	}

	/**
	 * Get the growth stage of the crop as a percentage of the maximum age.
	 *
	 * @param block
	 * @return int percentage growth progress
	 */
	public int getProgress(Block block) {
		if (plugin.isItemsAdder()) {
			CustomCrop customCrop = CustomCrop.byAlreadyPlaced(block);
			if (customCrop != null) {
				 return customCrop.getAge() * 100 / customCrop.getMaxAge();
			}
		}

		int progress = 0;
		BlockData bdata = block.getBlockData();
		if (bdata instanceof Ageable) {
			Ageable age = (Ageable) bdata;
			progress = age.getAge() * 100 / age.getMaximumAge();
			if (progress == 0 && isFullyGrown(block)) {
				progress = 100;
			}
			text = getGrowthText();

		} else if (bdata instanceof Levelled) {
			Levelled levelled = (Levelled) bdata;
			progress = levelled.getLevel() * 100 / levelled.getMaximumLevel();
			text = getLevelText();

		} else if (bdata instanceof TurtleEgg) {
			TurtleEgg turtleEgg = (TurtleEgg) bdata;
			progress = turtleEgg.getHatch() * 100 / turtleEgg.getMaximumHatch();
			text = getGrowthText();

		} else if (bdata instanceof Beehive) {
			Beehive beehive = (Beehive) bdata;
			progress = beehive.getHoneyLevel() * 100 / beehive.getMaximumHoneyLevel();
			text = getLevelText();

		} else if (bdata instanceof Sapling) {
			Sapling sapling = (Sapling) bdata;
			progress = sapling.getStage() * 100 / sapling.getMaximumStage();
			text = getGrowthText();
		}

		return progress;
	}

	/**
	 * If CACTUS and SUGAR_CANE are at least 3 blocks tall then assume fully grown.
	 *
	 * @param block
	 * @return
	 */
	private boolean isFullyGrown(Block block) {
		Material material = block.getType();
		if (material != Material.CACTUS && material != Material.SUGAR_CANE) {
			return false;
		}
		return material == block.getRelative(BlockFace.DOWN).getType() && material == block.getRelative(BlockFace.DOWN, 2).getType();
	}

	/**
	 * CaveVinesPlant is not ageable but can hold berries like CAVE VINES, so lets not pick the berries.
	 *
	 * @param bdata
	 * @return
	 */
	public boolean isCaveVinesPlant(BlockData bdata) {
		if (bdata instanceof CaveVinesPlant) {
			CaveVinesPlant plant = (CaveVinesPlant) bdata;
			if (plant.getMaterial() == Material.CAVE_VINES_PLANT && plant.isBerries()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * The berries on Sweet Berry Bushes and on Cave Vines are picked by right-clicking the block.
	 *
	 * @param block
	 * @return true if the berries will be picked by a right-click.
	 */
	public boolean hasBerries(Block block) {
		return (block.getType() == Material.SWEET_BERRY_BUSH || block.getType() == Material.CAVE_VINES);
	}
}
