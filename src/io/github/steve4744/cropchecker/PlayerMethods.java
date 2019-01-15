package io.github.steve4744.cropchecker;

import org.bukkit.entity.Player;

public class PlayerMethods {
	
	public static boolean isHoeing(Player player) {
		return player.getInventory().getItemInMainHand().getType().toString().toLowerCase().contains("_hoe");
	}

}
