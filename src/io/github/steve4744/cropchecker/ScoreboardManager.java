package io.github.steve4744.cropchecker;

import java.util.ArrayList;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import io.github.steve4744.cropchecker.configuration.Configuration;

public class ScoreboardManager {

	private Scoreboard scoreboard;
	private CropChecker plugin;
	
	private HashMap<String, Scoreboard> scoreboardMap = new HashMap<String, Scoreboard>();
	private HashMap<String, BukkitTask> taskMap = new HashMap<String, BukkitTask>();

	public ScoreboardManager(CropChecker plugin) {
		this.plugin = plugin;
	}

	public void showProgress(Player player, Material crop, int growth) {
		//kill any previous scheduled tasks
		cancelTask(player);
		
		if (scoreboardMap.containsKey(player.getName())) {
			scoreboard = scoreboardMap.get(player.getName());
		} else {
			scoreboard = buildScoreboard();
			scoreboardMap.put(player.getName(), scoreboard);
		}
		//remove previous entry
		resetScoreboard(player);
		
		Objective o = scoreboard.getObjective(DisplaySlot.SIDEBAR);
		o.setDisplayName(ChatColor.GOLD.toString() + ChatColor.BOLD + getDisplayName(crop) + ChatColor.WHITE + getPadding(crop) + "%");
		o.getScore(getText() + ":").setScore(growth);
		player.setScoreboard(scoreboard);
		
		BukkitTask task = new BukkitRunnable() {
			@Override
			public void run() {
				resetScoreboard(player);
			}
		}.runTaskLater(plugin, 60);
		taskMap.put(player.getName(), task);
		
	}

	private Scoreboard buildScoreboard() {
		scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective o = scoreboard.registerNewObjective("CropChecker", "showgrowth", "CropChecker");
		o.setDisplaySlot(DisplaySlot.SIDEBAR);
		return scoreboard;
	}

	private void resetScoreboard(Player player) {
		scoreboard = scoreboardMap.get(player.getName());
		for (String entry : new ArrayList<String>(scoreboard.getEntries())) {
			scoreboard.resetScores(entry);
		}
	}
	
	private void cancelTask(Player player) {
		BukkitTask task = null;
		if (taskMap.containsKey(player.getName())) {
			task = taskMap.get(player.getName());
			task.cancel();
			taskMap.remove(player.getName());
		}
	}
	
	private String getDisplayName(Material crop) {
		FileConfiguration cfg = Configuration.getStringData();
		String cropname = crop.name().toLowerCase();
		return cfg.getString("crops." + cropname, crop.name());	
	}
	
	private String getText() {
		FileConfiguration cfg = Configuration.getStringData();
		return cfg.getString("text.growth", "Growth");	
	}
	
	private String getPadding(Material crop) {
		StringBuilder pad = new StringBuilder();
		int padlen = 5;
		String cropname = getDisplayName(crop);
		
		//we need extra padding if crop name is shorter than the text
		if (cropname.length() < getText().length()) {
			int mismatch = getText().length() - cropname.length();
			if (mismatch >= 6) {
				padlen = 12;
			} else if (mismatch >= 4) {
				padlen = 8;
			} 
		}
		for (int i = 0; i < padlen; i++) {
			pad.append(" ");
		}
		return pad.toString();
	}

}
