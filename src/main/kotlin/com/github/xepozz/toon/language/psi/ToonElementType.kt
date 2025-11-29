package com.github.xepozz.toon.language.psi

import com.github.xepozz.toon.language.ToonLanguage
import com.intellij.psi.tree.IElementType

class ToonElementType(debugName: String) :
    IElementType("ToonElementType($debugName)", ToonLanguage.INSTANCE)
