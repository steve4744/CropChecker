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
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import io.github.steve4744.cropchecker.CropChecker;


public class DisplayHandler {

	private final CropChecker plugin;

	public DisplayHandler(CropChecker plugin) {
		this.plugin = plugin;
	}

	public void getVisualMethod(Player player, Material crop, Integer progress) {
		if (plugin.getConfiguration().isScoreboardEnabled()) {
			plugin.getScoreboardManager().showProgress(player, crop, progress);
		}
		if (plugin.getConfiguration().isActionBarEnabled() && !isMC1_13()) {
			String message = plugin.getDataHandler().getDisplayName(crop) + " : " + progress + "%";
			ActionBar actionbar = new ActionBar(ChatColor.valueOf(plugin.getConfiguration().getActionBarColor()) + message);
			actionbar.sendBar(player);
		}
	}

	private boolean isMC1_13() {
		return Bukkit.getVersion().contains("1.13");
	}
}