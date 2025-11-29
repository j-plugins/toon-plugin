package com.github.xepozz.toon.language

import com.github.xepozz.toon.language.psi.ToonDirective
import com.github.xepozz.toon.language.psi.ToonValue
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.psi.PsiElement

class ToonAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        when (element) {
            is ToonDirective -> {
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .range(element.textRange)
                    .textAttributes(DIRECTIVE_HIGHLIGHT)
                    .create()
            }

            is ToonValue -> {
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .range(element.textRange)
                    .textAttributes(VALUE_HIGHLIGHT)
                    .create()
            }
        }
    }

    companion object Companion {
        val DIRECTIVE_HIGHLIGHT = TextAttributesKey.createTextAttributesKey(
            "TOON_PATTERN",
            DefaultLanguageHighlighterColors.KEYWORD,
        )
        private val VALUE_HIGHLIGHT = TextAttributesKey.createTextAttributesKey(
            "TOON_IDENTIFIER",
            DefaultLanguageHighlighterColors.STRING,
        )
    }
}