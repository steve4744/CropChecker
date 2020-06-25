# CropChecker

![alt text](https://www.spigotmc.org/attachments/2019-01-16_09-58-07-png.398269/ "CropChecker by steve4744")

Crop Checker is a simple utility plugin for Spigot/Minecraft 1.13 to 1.16. Well suited to Survival and Skyblock servers, it will allow players to display the growth status of any of their crops, as well as the status of other items, such as the honey level of bee hives.

Simply by right-clicking the crop/item with a hoe, it will briefly display on screen the name and age of the crop. The age is shown as a percentage from 0% (growth not started) to 100% (fully grown). The name of the crop/item displayed can be set or translated to any language in 'strings.yml'.

From Spigot/Minecraft 1.15, you can right-click a beehive or bee-nest to display its honey level.
From Spigot/Minecraft 1.14, you can right-click the Composter to display how full it is.

By default, the information is displayed for 3 seconds on a small Scoreboard on the right-hand side of the screen. If you prefer, the information can be displayed on a BossBar, using the bar to visually display progress. The bar colour, text colour and display time are all configurable in the 'config.yml' file.

Starting with Minecraft/Spigot 1.14, there is also the option to display the text on the ActionBar. The colour of the text can again be set in the config.yml file.

To use a specific item for right-clicking the crop or beehive, then an 'override_item' can be enabled in the config.yml. To right-click with an empty hand, the override item should be set to AIR.


## Dependencies
The latest version of this plugin is only supported on Spigot/Minecraft 1.16+.

No other plugins are required.


## Download
For Minecraft 1.16+, CropChecker can be [downloaded from Spigot](https://www.spigotmc.org/resources/cropchecker-check-crop-growth-progress.64044/ "CropChecker by steve4744")

For Minecraft 1.15+, download version 2.4 [from Spigot](https://www.spigotmc.org/resources/cropchecker-check-crop-growth-progress.64044/download?version=312297 "CropChecker v2.4")

For Minecraft 1.13 and 1.14, download version 2.0 [from Spigot](https://www.spigotmc.org/resources/cropchecker-check-crop-growth-progress.64044/download?version=299945/ "CropChecker v2.0")

For Minecraft 1.12, download version 0.1 [from GitHub](https://github.com/steve4744/CropChecker/releases/download/v0.1/CropChecker.jar "CropChecker v0.1")


## Commands & Permissions
__/cropchecker help__ help on running the plugin

__/cropchecker reload__ reload the config file (requires cropchecker.admin permission)


## Installation

    Download CropChecker.jar
    Copy to your server's 'plugins' folder
    Restart your server
    (Optional) Translate the crop names into your desired language by editing 'strings.yml'


Updated 25 June 2020 by steve4744
