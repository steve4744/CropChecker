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
