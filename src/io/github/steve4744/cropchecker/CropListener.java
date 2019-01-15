package io.github.steve4744.cropchecker;

import org.bukkit.Material;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class CropListener implements Listener {

	@EventHandler
	public void onCropCheck(PlayerInteractEvent event) {

		if (event.getAction() != Action.RIGHT_CLICK_BLOCK || event.getHand().equals(EquipmentSlot.OFF_HAND)) {
			return;
		}
		
		Player player = event.getPlayer();
		if (!PlayerMethods.isHoeing(player)) {
			return;
		}
		BlockData bdata = event.getClickedBlock().getBlockData();
		Material crop = bdata.getMaterial();
		if (bdata instanceof Ageable) {
			
			Ageable age = (Ageable) bdata;
			int growth = age.getAge() * 100 / age.getMaximumAge();

			CropChecker.getScoreboardManager().showProgress(player, crop, growth);
			event.setCancelled(true);
		}
	}

}
