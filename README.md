# CropChecker

![beetroots](https://user-images.githubusercontent.com/6975392/145026806-d5823ef7-3fd6-48be-9a3b-b6fca6650196.png "CropChecker by steve4744")

Crop Checker is a simple utility plugin for Spigot/Minecraft 1.13.2 to 1.21.4. Well suited to Survival and Skyblock servers, it will allow players to display the growth status of any of their crops, as well as the status of other items, such as the honey level of bee hives and the bonemeal level of composters.

Simply by right-clicking the crop or item with a hoe, it will briefly display on screen the name and age of the crop. The age is shown as a percentage from 0% (growth not started) to 100% (fully grown). The name of the crop or item displayed can be customised or translated to any language in 'strings.yml'.

By default, the information is displayed for 3 seconds on a small Scoreboard on the right-hand side of the screen. If you prefer, the information can be displayed on a BossBar, using the bar to visually display progress. The bar colour, text colour and display time are all configurable in the 'config.yml' file.

Starting with Minecraft/Spigot 1.14, there is also the option to display the text on the ActionBar. The colour of the text can again be set in the config.yml file.

To use a specific item for right-clicking the crop or beehive, then an 'override_item' can be enabled in the config.yml. To right-click with an empty hand, the override item should be set to AIR.


## Dependencies
The latest version of this plugin requires Java 21 and is supported on Spigot/Minecraft 1.20.6 - 1.21-4.

For other versions of Spigot/Minecraft, refer to the download list below.

No other plugins are required.


## Download
For Minecraft 1.20.6 - 1.21.4, CropChecker can be [downloaded from Spigot](https://www.spigotmc.org/resources/cropchecker-check-crop-growth-progress.64044/ "CropChecker by steve4744")

For Minecraft 1.20.4, download version 3.5 [from Spigot](https://www.spigotmc.org/resources/cropchecker-check-crop-growth-progress-honey-levels.64044/download?version=501538 "CropChecker v3.5")

For Minecraft 1.17 to 1.19, download version 3.4 [from Spigot](https://www.spigotmc.org/resources/cropchecker-check-crop-growth-progress-honey-levels.64044/download?version=478882/ "CropChecker v3.4")

For Minecraft 1.16, download version 2.5 [from Spigot](https://www.spigotmc.org/resources/cropchecker-check-crop-growth-progress.64044/download?version=339368 "CropChecker v2.5")

For Minecraft 1.15, download version 2.4 [from Spigot](https://www.spigotmc.org/resources/cropchecker-check-crop-growth-progress.64044/download?version=312297 "CropChecker v2.4")

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


Updated 19th December 2024 by steve4744
