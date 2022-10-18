/*
 * MIT License

Copyright (c) 2022 steve4744

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

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CropCheckerCommand implements CommandExecutor {

	private final CropChecker plugin;
	private final String version;

	public CropCheckerCommand(CropChecker plugin, String version) {
		this.plugin = plugin;
		this.version = version;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		String infoMessage = ChatColor.GREEN + "[CropChecker] " + ChatColor.WHITE + "Version " + version + " : plugin by "+ ChatColor.AQUA + "steve4744";
		if (args.length > 0) {
			if (args[0].equalsIgnoreCase("reload")) {
				if (!sender.hasPermission("cropchecker.admin")) {
					sender.sendMessage(ChatColor.GREEN + "[CropChecker] " + ChatColor.WHITE + "You do not have permission to reload the plugin");
					return true;
				}
				plugin.reloadPlugin();
				sender.sendMessage(ChatColor.GREEN + "[CropChecker] " + ChatColor.WHITE + "Config reloaded");
				return false;
			} else if (args[0].equalsIgnoreCase("help")) {
				sender.sendMessage(ChatColor.GREEN + "[CropChecker] " + ChatColor.WHITE + "Right-click crops with a hoe to check growth");
				return false;
			}
		}
		sender.sendMessage(infoMessage);
		return true;
	}
}
