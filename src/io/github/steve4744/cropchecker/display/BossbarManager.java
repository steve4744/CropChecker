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

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import io.github.steve4744.cropchecker.CropChecker;
import io.github.steve4744.cropchecker.configuration.Configuration;

public class BossbarManager {

	private CropChecker plugin;
	private Configuration config;
	private static Map<String, BossBar> barmap = new HashMap<String, BossBar>();
	private static Map<String, BukkitTask> taskmap = new HashMap<String, BukkitTask>();

	public BossbarManager(CropChecker plugin) {
		this.plugin = plugin;
		this.config = plugin.getConfiguration();
	}

	private void createBar(Player player) {
		String colour = config.getBossbarColor();
		BossBar bar = Bukkit.createBossBar(null, BarColor.valueOf(colour), BarStyle.SEGMENTED_10);
		bar.addPlayer(player);
		barmap.put(player.getName(), bar);
	}

	private void removePlayerFromBar(Player player) {
		BossBar bar = barmap.get(player.getName());
		bar.removePlayer(player);
		barmap.remove(player.getName());
		bar = null;
	}

	public void setBar(Player player, String message, int progress) {
		//kill any previous scheduled tasks
		cancelTask(player.getName());
		
		if (!barmap.containsKey(player.getName())) {
			createBar(player);
		}
		String colour = config.getBossbarTextColor();
		barmap.get(player.getName()).setTitle(ChatColor.valueOf(colour) + message);
		barmap.get(player.getName()).setProgress((double)progress / 100);
		
		BukkitTask task = new BukkitRunnable() {
			@Override public void run() {
				removePlayerFromBar(player);
			}
		}.runTaskLater(plugin, plugin.getDisplayHandler().getDisplayTime());
		taskmap.put(player.getName(), task);
	}

	private void cancelTask(String playername) {
		BukkitTask task = null;
		if (taskmap.containsKey(playername)) {
			task = taskmap.get(playername);
			task.cancel();
			taskmap.remove(playername);
		}
	}

}
