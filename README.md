# BestViewDistance
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

## Commands
```
/view server => Get reduction indice.
/view tps => Get server's tps.
/view ping <player> => Get player ping.
/view <player> => Get player view distance.
/view limit => View config limits.

Permission : view.check
```

## Config
```
ViewDistance:
  Min: 4 // Min View Distance. Keep it above 3.
  Max: 16 // Max View Distance.
  Delay: 20 // Delay between calculations, in seconds. Keep it above 15.
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


## Infos
Spigot Link : [https://www.spigotmc.org/resources/best-view-distance.61963/](https://www.spigotmc.org/resources/best-view-distance.61963/)
Github Link : [https://github.com/Lxct/BestViewDistance](https://github.com/Lxct/BestViewDistance)