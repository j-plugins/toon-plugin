package com.github.xepozz.toon.language

import com.github.xepozz.toon.ToonIcons
import com.intellij.openapi.fileTypes.LanguageFileType

class ToonFileType private constructor() : LanguageFileType(ToonLanguage.INSTANCE) {
    override fun getName() = "TOON"
    override fun getDescription() = "Token oriented object notation"
    override fun getDefaultExtension() = "txt"
    override fun getIcon() = ToonIcons.FILE

    companion object Companion {
        @Suppress("unused")
        @JvmStatic
        val INSTANCE = ToonFileType()
    }
}