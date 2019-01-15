package io.github.steve4744.cropchecker;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class CropChecker extends JavaPlugin {
	
	private static CropChecker instance;
	private ScoreboardManager scoreboardManager;

	@Override
	public void onEnable() {

		instance = this;
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new CropListener(), this);		
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

}
