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

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import io.github.steve4744.cropchecker.configuration.Configuration;
import io.github.steve4744.cropchecker.data.DataHandler;
import io.github.steve4744.cropchecker.display.DisplayHandler;
import io.github.steve4744.cropchecker.display.ScoreboardManager;
import io.github.steve4744.cropchecker.metrics.Metrics;

public class CropChecker extends JavaPlugin {

	private String version;
	private ScoreboardManager scoreboardManager;
	private DataHandler dataHandler;
	private Configuration configuration;
	private DisplayHandler displayHandler;
	private PlayerHandler playerHandler;
	private boolean itemsadder;

	@Override
	public void onEnable() {

		this.saveDefaultConfig();
		getConfig().options().copyDefaults(true);
		saveConfig();
		version = this.getDescription().getVersion();

		this.getCommand("cropchecker").setExecutor(new CropCheckerCommand(this, version));
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new CropListener(this), this);
		configuration = new Configuration(this);
		dataHandler = new DataHandler(this);
		displayHandler = new DisplayHandler(this);
		playerHandler = new PlayerHandler(this);

		Plugin ItemsAdder = pm.getPlugin("ItemsAdder");
		if (ItemsAdder != null && ItemsAdder.isEnabled()) {
			itemsadder = true;
			getLogger().info("Successfully linked with ItemsAdder, version " + ItemsAdder.getDescription().getVersion());
		}

		new Metrics(this, 3901);
		checkForUpdate();
	}

	@Override
	public void onDisable() {
		dataHandler = null;
		displayHandler = null;
		scoreboardManager = null;
		getLogger().info("CropChecker disabled");
	}

	public ScoreboardManager getScoreboardManager() {
		if (scoreboardManager == null) {
			scoreboardManager = new ScoreboardManager(this);
		}
		return scoreboardManager;
	}

	public DataHandler getDataHandler() {
		return dataHandler;
	}

	public DisplayHandler getDisplayHandler() {
		return displayHandler;
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public PlayerHandler getPlayerHandler() {
		return playerHandler;
	}

	public boolean isItemsAdder() {
		return itemsadder;
	}

	private void checkForUpdate() {
		if (!getConfig().getBoolean("Check_For_Update", true)) {
			return;
		}
		new BukkitRunnable() {
			@Override
			public void run() {
				String latestVersion = VersionChecker.getVersion();
				if (latestVersion == "error") {
					getLogger().info("Error attempting to check for new version. Please report it here: https://www.spigotmc.org/threads/cropchecker-check-crop-growth-progress.355898/");
				} else {
					if (!version.equals(latestVersion)) {
						getLogger().info("New version " + latestVersion + " available on Spigot: https://www.spigotmc.org/resources/cropchecker-check-crop-growth-progress.64044/");
					}
				}
			}
		}.runTaskLaterAsynchronously(this, 20L);
	}

	public void reloadPlugin() {
		reloadConfig();
		configuration.reloadStrings();
	}

}
