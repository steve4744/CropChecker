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
package io.github.steve4744.cropchecker;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class PlayerHandler {

	private CropChecker plugin;

	public PlayerHandler(CropChecker plugin) {
		this.plugin = plugin;
	}

	public boolean hasCorrectTool(Player player) {
		if (!plugin.getConfig().getBoolean("override_tool.enabled")) {
			return isHoeing(player);
		}
		return hasTool(player);
	}

	private boolean isHoeing(Player player) {
		return player.getInventory().getItemInMainHand().getType().toString().toLowerCase().contains("_hoe");
	}

	private boolean hasTool(Player player) {
		Material tool = Material.getMaterial(plugin.getConfig().getString("override_tool.item", "AIR").toUpperCase());
		return tool == player.getInventory().getItemInMainHand().getType();
	}
}
