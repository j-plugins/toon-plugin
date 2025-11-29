package com.github.xepozz.toon.language.psi

import com.github.xepozz.toon.language.ToonLanguage
import com.intellij.psi.tree.IElementType

class ToonTokenType(debugName: String) : IElementType(debugName, ToonLanguage.INSTANCE) {
    override fun toString() = "ToonTokenType." + super.toString()
}