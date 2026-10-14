# Better Ping Display Reforged

Better Ping Display Reforged is a client-side Fabric mod that displays each player's latency as a number in the Minecraft player list. The server does not need the mod installed, and it works on vanilla servers.

This project continues the original [Better Ping Display [Fabric]](https://github.com/vladmarica/better-ping-display-fabric) project by Vlad Marica (Quintinity). It is a community-maintained port and is not the original author's project.

## Compatibility

- Minecraft Java Edition **26.2**
- Fabric Loader **0.19.5 or newer**

This branch targets Minecraft 26.2 only.

## Installation

1. Install Fabric Loader for Minecraft 26.2.
2. Download the Better Ping Display Reforged 26.2 jar from this repository's Releases.
3. Put the jar in your Minecraft `mods` folder and launch the Fabric profile.

Mod Menu and YetAnotherConfigLib (YACL) are optional. Install compatible versions of both to open the in-game settings screen from Mod Menu. Without them, the mod uses the values in `config/betterpingdisplay.json`.

## Configuration

The config file is `config/betterpingdisplay.json` and has these options:

| Option | Default | Description |
|---|---:|---|
| `autoColorPingText` | `true` | Color the ping text according to latency. |
| `renderPingBars` | `false` | Also render Minecraft's regular ping bars. |
| `pingTextColor` | `#A0A0A0` | Text color when automatic coloring is disabled. |
| `pingTextFormatString` | `%dms` | Format string for the latency; it must contain `%d`. |

## Credits and license

This project is based on [Better Ping Display [Fabric]](https://github.com/vladmarica/better-ping-display-fabric), originally created by Vlad Marica. Original package names and the original copyright notice are retained. The project remains under the original MIT License; see [LICENSE](LICENSE).

Repository: [henrymmey/better-ping-display-reforged](https://github.com/henrymmey/better-ping-display-reforged)
