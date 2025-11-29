package com.github.xepozz.toon.language

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.psi.FileViewProvider

class ToonFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, ToonLanguage.INSTANCE) {
    override fun getFileType() = ToonFileType.INSTANCE
}
