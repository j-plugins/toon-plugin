package com.github.xepozz.toon.language.parser

import com.github.xepozz.toon.language.psi.ToonTypes
import com.intellij.lexer.MergingLexerAdapter
import com.intellij.psi.tree.TokenSet

class ToonIndentingLexer : IndentingLexerAdapter(
    MergingLexerAdapter(
        ToonLexer(),
        TokenSet.create(ToonTypes.EOL),
    ),
    ToonTypes.EOL,
    ToonTypes.INDENT,
    ToonTypes.DEDENT,
)