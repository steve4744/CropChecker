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
package io.github.steve4744.cropchecker.display;

import org.bukkit.entity.Player;

import io.github.steve4744.cropchecker.CropChecker;

public class DisplayHandler {

	private final CropChecker plugin;

	public DisplayHandler(CropChecker plugin) {
		this.plugin = plugin;
	}

	/**
	 * Get the enabled display option(s) for presenting the crop name and progress.
	 *
	 * @param player
	 * @param block
	 * @param progress
	 */
	public void getVisualMethod(Player player, String displayName, Integer progress) {
		if (plugin.getConfiguration().isScoreboardEnabled()) {
			plugin.getScoreboardManager().showProgress(player, displayName, progress);
		}

		if (plugin.getConfiguration().isActionBarEnabled()) {
			String message = displayName + " : " + progress + "%";
			ActionBar actionbar = new ActionBar(plugin.getConfiguration().getActionBarColor() + message);
			actionbar.sendBar(player);
		}

		if (plugin.getConfiguration().isBossbarEnabled()) {
			String message = displayName + " : " + progress + "%";
			BossbarManager bm = new BossbarManager(plugin);
			bm.setBar(player, message, progress);
		}
	}
}
