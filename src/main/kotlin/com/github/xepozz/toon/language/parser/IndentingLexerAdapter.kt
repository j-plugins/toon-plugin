package com.github.xepozz.toon.language.parser

import com.intellij.lexer.Lexer
import com.intellij.lexer.LookAheadLexer
import com.intellij.psi.tree.IElementType

abstract class IndentingLexerAdapter(
    lexer: Lexer,
    private val eolTokenType: IElementType,
    private val indentTokenType: IElementType,
    private val dedentTokenType: IElementType,
) : LookAheadLexer(lexer) {
    private val indentStack = mutableListOf(0)

    override fun lookAhead(baseLexer: Lexer) {
        val tokenType = baseLexer.tokenType

        if (tokenType == null) {
            val endOffset = baseLexer.tokenEnd
            while (indentStack.size > 1) {
                indentStack.removeLast()
                addToken(endOffset, dedentTokenType)
            }
            advanceAs(baseLexer, tokenType)
            return
        }

        if (tokenType != eolTokenType) {
            advanceAs(baseLexer, tokenType)
            return
        }

        // Emit the newline token
        advanceAs(baseLexer, tokenType)

        // Inspect the beginning of the next line
        val buffer = baseLexer.bufferSequence
        val bufferEnd = baseLexer.bufferEnd

        var index = baseLexer.tokenStart
        if (index >= bufferEnd) return

        var columns = 0

        // Count leading spaces
        while (index < bufferEnd) {
            val c = buffer[index]
            when (c) {
                ' ' -> {
                    columns++
                    index++
                }

                '\r', '\n' -> return
                else -> break
            }
        }

        if (index >= bufferEnd) return

        val newIndent = columns
        val currentIndent = indentStack.last()

        val logicalLineStart = index // Actual content starts here

        when {
            newIndent > currentIndent -> {
                indentStack.add(newIndent)
                addToken(logicalLineStart, indentTokenType)
            }

            newIndent < currentIndent -> {
                while (indentStack.size > 1 && newIndent < indentStack.last()) {
                    indentStack.removeLast()
                    addToken(logicalLineStart, dedentTokenType)
                }
            }

            else -> {
                // Same indent – nothing to emit
            }
        }
    }
}
