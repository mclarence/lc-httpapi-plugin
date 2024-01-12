# LunarCore HTTP API Plugin

---
HTTP API plugin for LunarCore server for a specific turn based anime game.

## Roadmap
- [x] Hook into plugin system of LunarCore.
- [ ] Authentication
- [ ] Endpoints
  - [x] Get all avatars (characters) from the game.
  - [x] Get all items (consumables, relics etc...) from the game.
  - [ ] Give items to players
  - [ ] Give relics (with custom main and sub stats) to players.
  - [ ] Get all relics.
  - [ ] Get player list.

## Compiling
```shell
mvn package assembly:single
```

## Installation
Put the jar file into the plugins folder in LunarCore directory.

# Contributing
Contributions are welcome, please open pull request.
