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

%{
private int prevIndent = 0;
private boolean atLineStart = true;
%}


NEWLINE=\r\n|\r|\n
WHITESPACE=[ \t]+
ALPHA=[A-Za-z]
NUMBER=[\d]+(.[\d]+)?
QUOTTED_STRING = "\""(\\\"|[^\"])*"\"" | "\'"(\\\'|[^\'])*"\'"

%state VALUE, TABLE_SIZE, MAIN
%%

<YYINITIAL> {
    #[^\n]*       { return ToonTypes.COMMENT; }
    ([\w]+)       { return ToonTypes.TEXT; }
    ":"           { yybegin(VALUE); return ToonTypes.DELIMITER; }
    "["           { yybegin(TABLE_SIZE); return ToonTypes.LBRACKET; }
}

<TABLE_SIZE> {
    {NUMBER}          { return ToonTypes.NUMBER; }
    "#"               { return ToonTypes.SHARP; }
    "]"               { yybegin(MAIN); return ToonTypes.RBRACKET; }
}

<VALUE> {
    "null"                  { return ToonTypes.NULL; }
    "false"|"true"          { return ToonTypes.BOOLEAN; }
    ","                     { return ToonTypes.COMMA; }
    {NUMBER}                { return ToonTypes.NUMBER; }
    {ALPHA}[^\n,]*          { return ToonTypes.TEXT; }
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
{NEWLINE}               { atLineStart = true; yybegin(YYINITIAL); return ToonTypes.EOL; }

[^]                     { return TokenType.BAD_CHARACTER; }