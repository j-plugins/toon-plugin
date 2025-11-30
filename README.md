# TOON Plugin

![Build](https://github.com/j-plugins/toon-plugin/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/29200-toon.svg)](https://plugins.jetbrains.com/plugin/29200-toon)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/29200-toon.svg)](https://plugins.jetbrains.com/plugin/29200-toon)

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
