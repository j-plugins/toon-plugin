package com.github.xepozz.toon.language.parser;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import com.github.xepozz.toon.language.psi.ToonTypes;

%%
%class ToonLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}


NEWLINE=\r|\n|\r\n
WHITESPACE=[ \t]+

%state VALUE
%%

<YYINITIAL> {
    #[^\n]*       { return ToonTypes.COMMENT; }
    ([\w-]+)      { return ToonTypes.TEXT; }
    ":"           { yybegin(VALUE); return ToonTypes.DELIMITER; }
}
<VALUE> {
    [^\s][^\n]*   { return ToonTypes.TEXT; }
}

{WHITESPACE}      { return TokenType.WHITE_SPACE; }
{NEWLINE}         { yybegin(YYINITIAL); return ToonTypes.EOL; }
