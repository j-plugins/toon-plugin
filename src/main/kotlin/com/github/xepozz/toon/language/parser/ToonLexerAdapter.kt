package com.github.xepozz.toon.language.parser

import com.intellij.lexer.FlexAdapter

class ToonLexerAdapter : FlexAdapter(ToonLexer(null))