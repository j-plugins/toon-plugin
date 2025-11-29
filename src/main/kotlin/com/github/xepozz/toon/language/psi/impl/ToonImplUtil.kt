package com.github.xepozz.toon.language.psi.impl

import com.github.xepozz.toon.language.psi.ToonValue
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry

object ToonImplUtil {
    @JvmStatic
    fun getValue(element: ToonValue): String = element.text

    @JvmStatic
    fun getReferences(element: PsiElement): Array<PsiReference> =
        ReferenceProvidersRegistry.getReferencesFromProviders(element)
}