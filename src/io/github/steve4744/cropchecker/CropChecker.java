package io.github.steve4744.cropchecker;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import io.github.steve4744.cropchecker.configuration.Configuration;
import io.github.steve4744.cropchecker.metrics.Metrics;


public class CropChecker extends JavaPlugin {
	
	private String version;
	private static CropChecker instance;
	private ScoreboardManager scoreboardManager;

	@Override
	public void onEnable() {

		instance = this;
		this.saveDefaultConfig();
		version = this.getDescription().getVersion();
		
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new CropListener(), this);
		
		new Configuration(this);
		new Metrics(this);
		
		checkForUpdate();
	}
		
	@Override
	public void onDisable() {
		getLogger().info("CropChecker disabled");
	}
	
	public static CropChecker getPlugin() {
		return instance;
    }
	
	public static ScoreboardManager getScoreboardManager() {
        if (getPlugin().scoreboardManager == null) {
            getPlugin().scoreboardManager = new ScoreboardManager(instance);
        }
        return getPlugin().scoreboardManager;
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

}
