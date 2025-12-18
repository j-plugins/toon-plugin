package com.github.xepozz.toon.language.parser

import com.github.xepozz.toon.language.psi.ToonTypes
import com.intellij.lexer.FlexAdapter
import com.intellij.lexer.Lexer
import com.intellij.lexer.LookAheadLexer

class ToonLexerAdapter : LookAheadLexer(FlexAdapter(ToonLexer(null))) {
    private val indentStack = mutableListOf(0)
    private var start = false

    override fun lookAhead(baseLexer: Lexer) {
        val tokenType = baseLexer.tokenType

        println("tokenType: $tokenType")
        if (tokenType == null) {
            if (!start) {
                start = true
                super.lookAhead(baseLexer)
                return
            }
            val endOffset = baseLexer.tokenEnd
            println("compensation last: ${indentStack.size}, position: $endOffset")
            while (indentStack.size > 1) {
                indentStack.removeLast()
                addToken(endOffset, ToonTypes.DEDENT)
            }
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
                addToken(logicalLineStart, ToonTypes.INDENT)
            }

            newIndent < currentIndent -> {
                println("compensation in process: ${indentStack.size}, position: $logicalLineStart")
                while (indentStack.size > 1 && newIndent < indentStack.last()) {
                    indentStack.removeLast()
                    addToken(logicalLineStart, ToonTypes.DEDENT)
                }
            }
            else -> {
                // Same indent – nothing to emit
            }
        }
    }
}
