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
//            println("compensation last: ${indentStack.size}, offset: $endOffset")
            while (indentStack.size > 1) {
                indentStack.remove(indentStack.size - 1)
                addToken(endOffset, dedentTokenType)
//                println("tokenType(emulate): $dedentTokenType")
            }
//            println("tokenType: $tokenType")

            advanceAs(baseLexer, tokenType)
            return
        }

//        println("tokenType: $tokenType")
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

                '\t' -> {
                    columns += 2
                    index += 2
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
//                println("tokenType(emulate): $indentTokenType")
            }

            newIndent < currentIndent -> {
//                println("compensation in process: ${indentStack.size}, offset: $logicalLineStart")
                while (indentStack.size > 1 && newIndent < indentStack.last()) {
                    indentStack.remove(indentStack.size - 1)
                    addToken(logicalLineStart, dedentTokenType)
//                    println("tokenType(emulate): $dedentTokenType")
                }
            }

            else -> {
                // Same indent – nothing to emit
            }
        }
    }
}
