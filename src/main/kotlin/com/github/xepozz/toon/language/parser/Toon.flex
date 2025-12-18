package com.github.xepozz.toon.language.parser;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import com.github.xepozz.toon.language.psi.ToonTypes;

%%
%class _ToonLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

NEWLINE=\r\n|\r|\n
WHITESPACE=[ \t]+
ALPHA=[A-Za-z]
NUMBER=[\d]+(.[\d]+)?
QUOTTED_STRING = "\""(\\\"|[^\"])*"\"" | "\'"(\\\'|[^\'])*"\'"

%state VALUE, TABLE_SIZE
%%

<YYINITIAL> {
    #[^\r\n]*       { return ToonTypes.COMMENT; }
    ([\w]+)       { return ToonTypes.TEXT; }
    ":"           { yybegin(VALUE); return ToonTypes.DELIMITER; }
    "["           { yybegin(TABLE_SIZE); return ToonTypes.LBRACKET; }
}

<TABLE_SIZE> {
    {NUMBER}          { return ToonTypes.NUMBER; }
    "#"               { return ToonTypes.SHARP; }
    "]"               { yybegin(YYINITIAL); return ToonTypes.RBRACKET; }
}

<VALUE> {
    "null"                  { return ToonTypes.NULL; }
    "false"|"true"          { return ToonTypes.BOOLEAN; }
    ","                     { return ToonTypes.COMMA; }
    {NUMBER}                { return ToonTypes.NUMBER; }
    {ALPHA}[^\r\n,]*        { return ToonTypes.TEXT; }
    {QUOTTED_STRING}        { return ToonTypes.TEXT; }
    {WHITESPACE}            { return TokenType.WHITE_SPACE; }
}


// Special symbols
","                     { return ToonTypes.COMMA; }
"{"                     { return ToonTypes.LBRACE; }
"}"                     { return ToonTypes.RBRACE; }
"("                     { return ToonTypes.LPAREN; }
")"                     { return ToonTypes.RPAREN; }
"["                     { return ToonTypes.LBRACKET; }
"]"                     { return ToonTypes.RBRACKET; }
"-"                     { return ToonTypes.DASH; }
{WHITESPACE}            { return TokenType.WHITE_SPACE; }
{NEWLINE}               { yybegin(YYINITIAL); return ToonTypes.EOL; }

[^]                     { return TokenType.BAD_CHARACTER; }