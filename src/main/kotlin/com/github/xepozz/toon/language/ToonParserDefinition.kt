package com.github.xepozz.toon.language

import com.github.xepozz.toon.language.parser.ToonIndentingLexer
import com.github.xepozz.toon.language.parser.ToonParser
import com.github.xepozz.toon.language.psi.ToonTokenSets
import com.github.xepozz.toon.language.psi.ToonTypes
import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.psi.FileViewProvider
import com.intellij.psi.tree.IFileElementType

class ToonParserDefinition : ParserDefinition {
    override fun createLexer(project: com.intellij.openapi.project.Project?) = ToonIndentingLexer()

    override fun getWhitespaceTokens() = ToonTokenSets.WHITESPACES

    override fun createParser(project: com.intellij.openapi.project.Project?) = ToonParser()

    override fun getFileNodeType() = FILE

    override fun getCommentTokens() = ToonTokenSets.COMMENTS

    override fun getStringLiteralElements() = ToonTokenSets.STRING_LITERALS

    override fun createElement(node: ASTNode) = ToonTypes.Factory.createElement(node)

    override fun createFile(viewProvider: FileViewProvider) = ToonFile(viewProvider)

    override fun spaceExistenceTypeBetweenTokens(left: ASTNode?, right: ASTNode?) =
        ParserDefinition.SpaceRequirements.MAY


    companion object Companion {
        val FILE = IFileElementType(ToonLanguage.INSTANCE)
    }
}
