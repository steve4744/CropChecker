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

import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Levelled;
import org.bukkit.block.data.type.Beehive;
import org.bukkit.block.data.type.Sapling;
import org.bukkit.block.data.type.TurtleEgg;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class CropListener implements Listener {

	private final CropChecker plugin;

	public CropListener(CropChecker plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onCropCheck(PlayerInteractEvent event) {
		if (event.getAction() != Action.RIGHT_CLICK_BLOCK || event.getHand().equals(EquipmentSlot.OFF_HAND)) {
			return;
		}
		Player player = event.getPlayer();
		if (!player.hasPermission("cropchecker.use")) {
			return;
		}

		if (!plugin.getPlayerHandler().hasCorrectTool(player)) {
			return;
		}

		Block block = event.getClickedBlock();
		BlockData bdata = block.getBlockData();

		if (plugin.getDataHandler().isCaveVinesPlant(bdata)) {
			event.setCancelled(true);
			return;
		}
		if (!(bdata instanceof Ageable || bdata instanceof Levelled || bdata instanceof TurtleEgg || bdata instanceof Beehive || bdata instanceof Sapling)) {
			return;
		}

		if (plugin.getDataHandler().getDisplayName(block).isEmpty()) {
			return;
		}

		plugin.getDisplayHandler().getVisualMethod(player, block, plugin.getDataHandler().getProgress(block));
		event.setCancelled(true);
	}

}
