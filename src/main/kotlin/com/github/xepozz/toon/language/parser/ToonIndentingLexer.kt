package com.github.xepozz.toon.language.parser

import com.github.xepozz.toon.language.psi.ToonTypes

class ToonIndentingLexer : IndentingLexerAdapter(
    ToonLexer(),
    ToonTypes.EOL,
    ToonTypes.INDENT,
    ToonTypes.DEDENT,
)