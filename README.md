# toon-plugin

![Build](https://github.com/xepozz/toon-plugin/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/MARKETPLACE_ID.svg)](https://plugins.jetbrains.com/plugin/MARKETPLACE_ID)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/MARKETPLACE_ID.svg)](https://plugins.jetbrains.com/plugin/MARKETPLACE_ID)

## Template ToDo list
- [x] Create a new [IntelliJ Platform Plugin Template][template] project.
- [ ] Get familiar with the [template documentation][template].
- [ ] Adjust the [pluginGroup](./gradle.properties) and [pluginName](./gradle.properties), as well as the [id](./src/main/resources/META-INF/plugin.xml) and [sources package](./src/main/kotlin).
- [ ] Adjust the plugin description in `README` (see [Tips][docs:plugin-description])
- [ ] Review the [Legal Agreements](https://plugins.jetbrains.com/docs/marketplace/legal-agreements.html?from=IJPluginTemplate).
- [ ] [Publish a plugin manually](https://plugins.jetbrains.com/docs/intellij/publishing-plugin.html?from=IJPluginTemplate) for the first time.
- [ ] Set the `MARKETPLACE_ID` in the above README badges. You can obtain it once the plugin is published to JetBrains Marketplace.
- [ ] Set the [Plugin Signing](https://plugins.jetbrains.com/docs/intellij/plugin-signing.html?from=IJPluginTemplate) related [secrets](https://github.com/JetBrains/intellij-platform-plugin-template#environment-variables).
- [ ] Set the [Deployment Token](https://plugins.jetbrains.com/docs/marketplace/plugin-upload.html?from=IJPluginTemplate).
- [ ] Click the <kbd>Watch</kbd> button on the top of the [IntelliJ Platform Plugin Template][template] to be notified about releases containing new features and fixes.
- [ ] Configure the [CODECOV_TOKEN](https://docs.codecov.com/docs/quick-start) secret for automated test coverage reports on PRs

<!-- Plugin description -->

[GitHub](https://github.com/j-plugins/toon-plugin) | [Telegram](https://t.me/jb_plugins/708) | [Donation](https://github.com/xepozz/xepozz?tab=readme-ov-file#become-a-sponsor) | [Official website](https://toonformat.dev/)

## TOON syntax support

TOON (**T**oken-**O**riented **O**bject **N**otation) syntax support:

- syntax highlighting
- patterns matching (soon)
- indentation support (soon)
- JSON/Yaml <-> TOON converter (soon)

TOON is a compact, human-readable encoding of the JSON data model for LLM prompts.

### Token-Efficient & Accurate

TOON reaches 74% accuracy (vs JSON's 70%) while using ~40% fewer tokens in mixed-structure benchmarks across 4 models.

### JSON Data Model
Encodes the same objects, arrays, and primitives as JSON with deterministic, lossless round-trips.

### LLM-Friendly Guardrails
Explicit [N] lengths and {fields} headers give models a clear schema to follow, improving parsing reliability.

### Minimal Syntax
Uses indentation instead of braces and minimizes quoting, giving YAML-like readability with CSV-style compactness.

### Tabular Arrays
Uniform arrays of objects collapse into tables that declare fields once and stream row values line by line.

### Multi-Language Ecosystem
Spec-driven implementations in TypeScript, Python, Go, Rust, .NET, and other languages.

## Donation

Open-source tools can greatly improve workflows, helping developers and businesses save time and increase revenue.
Many successful projects have been built on these tools, benefiting a wide community.
However, maintaining and enhancing these resources requires continuous effort and investment.

Support from the community helps keep these projects alive and ensures they remain useful for everyone.
Donations play a key role in sustaining and improving these open-source initiatives.

Chose the best option for you to say thank you:

[<img height="28" src="https://github.githubassets.com/assets/patreon-96b15b9db4b9.svg"> Patreon](https://patreon.com/xepozz)
|
[<img height="28" src="https://github.githubassets.com/assets/buy_me_a_coffee-63ed78263f6e.svg"> Buy me a coffee](https://buymeacoffee.com/xepozz)
|
[<img height="28" src="https://boosty.to/favicon.ico"> Boosty](https://boosty.to/xepozz)

<!-- Plugin description end -->

## Installation

- Using the IDE built-in plugin system:

  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "toon-plugin"</kbd> >
  <kbd>Install</kbd>

- Using JetBrains Marketplace:

  Go to [JetBrains Marketplace](https://plugins.jetbrains.com/plugin/MARKETPLACE_ID) and install it by clicking the <kbd>Install to ...</kbd> button in case your IDE is running.

  You can also download the [latest release](https://plugins.jetbrains.com/plugin/MARKETPLACE_ID/versions) from JetBrains Marketplace and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>

- Manually:

  Download the [latest release](https://github.com/xepozz/toon-plugin/releases/latest) and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>


---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template
[docs:plugin-description]: https://plugins.jetbrains.com/docs/intellij/plugin-user-experience.html#plugin-description-and-presentation
