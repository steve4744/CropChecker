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
package io.github.steve4744.cropchecker.display;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import io.github.steve4744.cropchecker.CropChecker;

public class DisplayHandler {

	private final CropChecker plugin;

	public DisplayHandler(CropChecker plugin) {
		this.plugin = plugin;
	}

	/**
	 * Get the enabled display option(s) for presenting the crop name and progress.
	 * @param player
	 * @param block
	 * @param progress
	 */
	public void getVisualMethod(Player player, Block block, Integer progress) {
		if (plugin.getConfiguration().isScoreboardEnabled()) {
			plugin.getScoreboardManager().showProgress(player, block, progress);
		}

		if (plugin.getConfiguration().isActionBarEnabled() && !isMC1_13()) {
			String message = plugin.getDataHandler().getDisplayName(block) + " : " + progress + "%";
			ActionBar actionbar = new ActionBar(message, plugin.getConfiguration().getActionBarColor());
			actionbar.sendBar(player);
		}

		if (plugin.getConfiguration().isBossbarEnabled()) {
			String message = plugin.getDataHandler().getDisplayName(block) + " : " + progress + "%";
			BossbarManager bm = new BossbarManager(plugin);
			bm.setBar(player, message, progress);
		}
	}

	/**
	 * The number of seconds between 1 and 8 (default 3) to display the on-screen info
	 * @return number of seconds in ticks 
	 */
	public int getDisplayTime() {
		int seconds = plugin.getConfig().getInt("display_time", 3);
		if (seconds < 1 || seconds > 8) {
			seconds = 3;
		}
		return seconds * 20;
	}

	private boolean isMC1_13() {
		return Bukkit.getVersion().contains("1.13");
	}
}
