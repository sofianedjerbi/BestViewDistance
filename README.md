# BestViewDistance
![Picture of BVD](https://www.spigotmc.org/attachments/2018-12-01_09-00-48-png.388902/)  
A view distance plugin for Paper. Created to boost ping/tps and view distance.

## How it works ?
This plugin chooses the **best view distance for your players**.
The **view distance** is calculated based on your ping and the **TPS of the server**.
If the TPS are low, the view distance is reduced by a percentage called "*reduction indice*".
The reduction indice changes according to the TPS of the server.

This plugin **reduces players lag**, reduces players **ping** and **optimizes** your server a **better view distance**.

## Dependencies
Please download Paper !
https://papermc.io
It's better than spigot and bukkit.
Also provide you a lot of optimizations and options.
[WARNING] ProtocolLib is needed ONLY for 1.12.X !

## Commands / Permissions
```
/view server => Get reduction indice.
/view tps => Get server's tps.
/view ping <player> => Get player ping.
/view <player> => Get player actual view distance and his max view distance.
/view => Get help

Permission : "view.check"

/view reload => Reload plugin config. Need "view.reload"
/vdist => Allows your players to see their own view distance. Need "view.info"
/vping => Allows your players to see their own ping. Need "view.info"

Permission for update checker : "view.update"

Bypass algorithm : "view.set.x" (3 to 32)
(Example : view.set.15 set a view dist of 15 chunks)
Does not work with "*"/"*.*" permissions nodes !
```

## Config
```
#   ╔╗ ┌─┐┌─┐┌┬┐  ╦  ╦┬┌─┐┬ ┬  ╔╦╗┬┌─┐┌┬┐┌─┐┌┐┌┌─┐┌─┐
#   ╠╩╗├┤ └─┐ │   ╚╗╔╝│├┤ │││   ║║│└─┐ │ ├─┤││││  ├┤
#   ╚═╝└─┘└─┘ ┴    ╚╝ ┴└─┘└┴┘  ═╩╝┴└─┘ ┴ ┴ ┴┘└┘└─┘└─┘
#       - Get a Better View Distance, By LXCT. -
#
# Donate: https://paypal.me/lxct

Version: 2.2 # Version of the config file. Don't change this value.

Features:
  UseLoginView: true # Use a custom view on login
  UseAFKView: true # Use a custom view if the player is AFK.
  UsePing: true # The plugin will give a custom view distance for each players
  UseTasks: true # Use tasks. Turn this off will reduce lags, but view distance will change slower.
  UseFlyingView: false # Use a custom view if the player is flying.
  UseTeleportView: false # Use a custom view on teleport. Can reduce freeze on teleport.
  UsePermissions: false # Enable permissions (view.set.x) to bypass algorithm.

ViewDistance:
  Min: 4 # Minimum view distance (Minimum: 3)
  Max: 16 # Maximum view distance (Maximum: 32)
  OnLogin: 4 # View distance on login
  OnAFK: 3 # AFK view distance (If UseAFKView is on true)
  OnTeleport: 4 # View distance on teleport (If UseTeleportView is on true)
  OnFlying: 12 # View distance if flying (If UseFlyingView is on true)
  MoreThanSettings: 0 # Add x chunks more than player's settings.

Delay:
  CalculationsDelay: 1 # Delay in seconds to actualize calculations
  SetViewDelay: 5 # Delay in seconds to actualize global view distance
  UnsetTeleportViewDelay: 3 # Delay in seconds to unset the OnTeleport custom view
  CheckFlyingDelay: 5 # Delay in seconds before set the OnFlying view distance
  AFKDelay: 90 # Delay in seconds before set the OnAFK view distance

Settings:
  TpsLimit: 19.5 # Below: Decrease Reduction Indice // Over: Increase Reduction Indice.
  TpsChangeIndice: 0.01 # How much we had to increase/decrease the reduction indice. 0.01 = 1%
  MaxReductionIndice: 0.75 # Maximum Reduction Indice (0.75 = 75%)
  SafePing: 1 # Set this value to 0 for local hosting.
  PingForReduction: 550 # Ping required to decrease view distance
  PingForAugmentation: 90 # Ping required to increase view distance

Permissions:
  BypassAFKView: true # Player with permissions (view.set.x) can bypass the "OnAFK" view.
  BypassTeleportView: true # Player with permissions (view.set.x) can bypass the "OnTeleport" view.
  BypassFlyingView: true # Player with permissions (view.set.x) can bypass the "OnFlying" view.

Misc:
  DecimalsTPS: 2 # How many decimals for the %VDIST_DECIMAL_TPS% placeholder
  DecimalsIndice: 2 # How many decimals for the %VDIST_REDUCTION_INDICE_DECIMAL% placeholder
  HideVdistLine4: false # Hide the 4th line of the /vdist command
  CheckUpdates: true # Check for updates
  Metrics: true # Send anonymous stats
```

+ Messages.yml File.

## PlaceholderAPI :
```
%VDIST_REDUCTION_INDICE% => Get the reduction indice in percentage
%VDIST_REDUCTION_INDICE_DECIMAL% => Get the reduction indice with decimals
%VDIST_TPS% => Get TPS
%VDIST_DECIMAL_TPS% => Get TPS with decimals
%VDIST_PLAYER_SETTINGS_VIEW% => Get the render distance in player's settings
%VDIST_PLAYER_SUPPORTED_VIEW% => Get the supported view distance of a player
%VDIST_PLAYER_CURRENT_VIEW% => Get the current view distance of a player
```

## Compilation
Bash/Zsh linux terminal :

```
git clone https://github.com/Lxct/BestViewDistance.git
cd BestViewDistance
sudo mvn clean install
```
The jar is in the `target/` folder.

## Contact
Add me on Discord => Lxct#9971
I'd like to hear your comments ! <3


## Info
Spigot Link: [https://www.spigotmc.org/resources/best-view-distance.61963/](https://www.spigotmc.org/resources/best-view-distance.61963/)
Github Link: [https://github.com/Lxct/BestViewDistance](https://github.com/Lxct/BestViewDistance)
Dev Builds: [https://ci.codemc.org/job/Lxct/job/BestViewDistance/](https://ci.codemc.org/job/Lxct/job/BestViewDistance/)
[![Build Status](https://ci.codemc.org/job/Lxct/job/BestViewDistance/badge/icon)](https://ci.codemc.org/job/Kugge/job/BestViewDistance/)