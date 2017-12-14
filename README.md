![ExperiencedFlight](https://www.spigotmc.org/data/resource_icons/48/48587.jpg?1508455469)
## ExperiencedFlight [![Discord](https://img.shields.io/discord/253637961776627712.svg)](https://discord.cyrien.us) [![Donate](https://img.shields.io/badge/Support-Me!-blue.svg)](https://cyrien.us/donate) 
Flights Powered by Experience
_______

_Announcement_: If you installed ExperiencedFlight 0.0.2 or lower version. It's recommended to regenerate the configuration. But if you do not want to do so. Please change the flight costs, the lowest flight cost is now 1.1.
If you don't change this, It will always default to 1.1.

## Description

Enable players to fly using their experience as flight fuel. As players fly, their experience and level decay by the value set on the config every two tick. When a player runs out of experience and levels, the player's flight becomes disabled and start falling.

## Features

#### Balanced

- Experience decay is relative to the player's level. So higher level, slower decay.
    
     >_Experience don't grow on trees_

#### Flight Class (Permission Based)

- Four different flight classes. Economy class, Business class, and First class.
- Flight classes have a specific permission:
  - Economy = expflight.economy
  - Business = expflight.business
  - First Class = expflight.firstclass
  
   >_Note: Players must have `expflight.expfly` permission before they can fly._

#### Light Weight Processing

- Coded my own "Air Traffic Controller" to monitor flights and make sure that they do not cause lag. Instead of checking for all players, the "Air Traffic Controller" only checks players that it had given flight clearance to. (When a player executes /expfly the "Air Traffic Controller" gives flight clearance to a player)

#### Configurable

- You can configure the flight cost and flight speed of each flight class. So if players can get their hands on many experiences. Just adjust the flight cost. Just like inflation ;)

#### Others

- Smart flight landings. If the flight is Auto-Disable, it will automatically disable experienced flight when the player lands and remove that player's flight clearance. So that player won't be able to fly again until the player does /expfly. But if Auto-Disable is off, it will instead just pause experience decay and will still be able to fly again until the player manually disable experienced flight by executing /expfly or until the player runs out of experience

- givelvl command. So if some of the admins are generous, they could give players some levels so they could fly.

- givexp command. Works with Minecraft’s math equation for experiences.


## Installation

1. Download ExperiencedFlight.jar [here](https://www.spigotmc.org/resources/experiencedflight.48587/).
2. Drop it to your plugins folder.
3. Start your server (will generate config).
4. Configure the plugin to your liking.
5. Do /expflight reload.
6. Done!

## Commands
| Command       | Usage         | Description  |
| ------------- |:-------------:| :-----:|
| /expfly       | /expfly       |Fly using experience |
| /givelvl      | /givelvl [player] [amount] |Give level to a player so they could fly |
| /givexp       | /givexp [player] [amount]    | Give xp to a player |
| /expflight    | /expflight [help or reload] | General ExperiencedFlight commands|

## Permissions

```yml
expflight.expfly:
    description: Allow players to use expfly command.
    default: op
expflight.economy:
    description: Expensive flight.
expflight.business:
    description: Averaged priced flight
expflight.firstclass:
    description: Very cheap flight.
    default: op
expflight.givelvl:
    description: Allow player’s to use /givelvl
    default: op
expflight.givexp:
    description: Allow player’s to use /givexp
    default: op
expflight.reload:
    description: Allow player to use /expflight reload
    default: op
```

Updates will be released...
