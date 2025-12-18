package com.github.xepozz.toon.language.parser

import com.github.xepozz.toon.language.psi.ToonTypes
import com.intellij.lexer.FlexAdapter
import com.intellij.lexer.Lexer
import com.intellij.lexer.LookAheadLexer

class ToonLexerAdapter : LookAheadLexer(FlexAdapter(ToonLexer(null))) {
    private val indentStack = mutableListOf(0)

    override fun lookAhead(baseLexer: Lexer) {
        val tokenType = baseLexer.tokenType

        if (tokenType == null) {
            addToken(ToonTypes.DEDENT)
            super.lookAhead(baseLexer)
            return
        }

        if (tokenType != ToonTypes.EOL) {
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
                addToken(ToonTypes.INDENT)
            }
            newIndent < currentIndent -> {
                while (indentStack.size > 1 && newIndent < indentStack.last()) {
                    indentStack.removeAt(indentStack.size - 1)
                    addToken(logicalLineStart, ToonTypes.DEDENT)
                }
            }
            else -> {
                // Same indent – nothing to emit
            }
        }
    }
}
