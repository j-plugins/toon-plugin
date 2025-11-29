package com.github.xepozz.toon.language.psi

import com.intellij.psi.tree.TokenSet

object ToonTokenSets {
    val EMPTY_SET = TokenSet.EMPTY

    val COMMENTS = TokenSet.create(ToonTypes.COMMENT)
    val STRING_LITERALS = TokenSet.create(ToonTypes.DIRECTIVE, ToonTypes.VALUE)
    val WHITESPACES = TokenSet.WHITE_SPACE
}