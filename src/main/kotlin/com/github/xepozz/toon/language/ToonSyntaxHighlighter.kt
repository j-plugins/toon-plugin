package com.github.xepozz.toon.language

import com.github.xepozz.toon.language.parser.ToonLexerAdapter
import com.github.xepozz.toon.language.psi.ToonTypes
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType

class ToonSyntaxHighlighter : SyntaxHighlighterBase() {
    override fun getHighlightingLexer() = ToonLexerAdapter()

    override fun getTokenHighlights(tokenType: IElementType) = when (tokenType) {
        ToonTypes.COMMENT -> COMMENT_KEYS
        TokenType.BAD_CHARACTER -> BAD_CHAR_KEYS
        else -> EMPTY_KEYS
    }

    companion object Companion {
        private val BAD_CHAR_KEYS = arrayOf(
            HighlighterColors.BAD_CHARACTER,
        )

        private val COMMENT_KEYS = arrayOf(
            DefaultLanguageHighlighterColors.DOC_COMMENT
        )
        private val EMPTY_KEYS = emptyArray<TextAttributesKey>()
    }
}