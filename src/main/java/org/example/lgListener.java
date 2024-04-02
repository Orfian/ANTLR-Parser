// Generated from C:/Users/Osoba/Documents/PJP/ANTLR-Parser/src/main/antlr/lg.g4 by ANTLR 4.13.1
package org.example;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link lgParser}.
 */
public interface lgListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link lgParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(lgParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link lgParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(lgParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by the {@code declarationStatement}
	 * labeled alternative in {@link lgParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterDeclarationStatement(lgParser.DeclarationStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code declarationStatement}
	 * labeled alternative in {@link lgParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitDeclarationStatement(lgParser.DeclarationStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code readStatement}
	 * labeled alternative in {@link lgParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterReadStatement(lgParser.ReadStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code readStatement}
	 * labeled alternative in {@link lgParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitReadStatement(lgParser.ReadStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code writeStatement}
	 * labeled alternative in {@link lgParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterWriteStatement(lgParser.WriteStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code writeStatement}
	 * labeled alternative in {@link lgParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitWriteStatement(lgParser.WriteStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code blockStatement}
	 * labeled alternative in {@link lgParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterBlockStatement(lgParser.BlockStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code blockStatement}
	 * labeled alternative in {@link lgParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitBlockStatement(lgParser.BlockStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifStatement}
	 * labeled alternative in {@link lgParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(lgParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifStatement}
	 * labeled alternative in {@link lgParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(lgParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code whileStatement}
	 * labeled alternative in {@link lgParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(lgParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code whileStatement}
	 * labeled alternative in {@link lgParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(lgParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code doWhileStatement}
	 * labeled alternative in {@link lgParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterDoWhileStatement(lgParser.DoWhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code doWhileStatement}
	 * labeled alternative in {@link lgParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitDoWhileStatement(lgParser.DoWhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expressionStatement}
	 * labeled alternative in {@link lgParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterExpressionStatement(lgParser.ExpressionStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expressionStatement}
	 * labeled alternative in {@link lgParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitExpressionStatement(lgParser.ExpressionStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code emptyStatement}
	 * labeled alternative in {@link lgParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterEmptyStatement(lgParser.EmptyStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code emptyStatement}
	 * labeled alternative in {@link lgParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitEmptyStatement(lgParser.EmptyStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parentheses}
	 * labeled alternative in {@link lgParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParentheses(lgParser.ParenthesesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parentheses}
	 * labeled alternative in {@link lgParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParentheses(lgParser.ParenthesesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code identifier}
	 * labeled alternative in {@link lgParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(lgParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by the {@code identifier}
	 * labeled alternative in {@link lgParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(lgParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicalNot}
	 * labeled alternative in {@link lgParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalNot(lgParser.LogicalNotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicalNot}
	 * labeled alternative in {@link lgParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalNot(lgParser.LogicalNotContext ctx);
	/**
	 * Enter a parse tree produced by the {@code concatenation}
	 * labeled alternative in {@link lgParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterConcatenation(lgParser.ConcatenationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code concatenation}
	 * labeled alternative in {@link lgParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitConcatenation(lgParser.ConcatenationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignment}
	 * labeled alternative in {@link lgParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(lgParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignment}
	 * labeled alternative in {@link lgParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(lgParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryMinus}
	 * labeled alternative in {@link lgParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryMinus(lgParser.UnaryMinusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryMinus}
	 * labeled alternative in {@link lgParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryMinus(lgParser.UnaryMinusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code variable}
	 * labeled alternative in {@link lgParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterVariable(lgParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code variable}
	 * labeled alternative in {@link lgParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitVariable(lgParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arithmetic}
	 * labeled alternative in {@link lgParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterArithmetic(lgParser.ArithmeticContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arithmetic}
	 * labeled alternative in {@link lgParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitArithmetic(lgParser.ArithmeticContext ctx);
	/**
	 * Enter a parse tree produced by the {@code relational}
	 * labeled alternative in {@link lgParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterRelational(lgParser.RelationalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code relational}
	 * labeled alternative in {@link lgParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitRelational(lgParser.RelationalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code equality}
	 * labeled alternative in {@link lgParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterEquality(lgParser.EqualityContext ctx);
	/**
	 * Exit a parse tree produced by the {@code equality}
	 * labeled alternative in {@link lgParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitEquality(lgParser.EqualityContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logical}
	 * labeled alternative in {@link lgParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLogical(lgParser.LogicalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logical}
	 * labeled alternative in {@link lgParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLogical(lgParser.LogicalContext ctx);
	/**
	 * Enter a parse tree produced by {@link lgParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(lgParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link lgParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(lgParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link lgParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(lgParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link lgParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(lgParser.LiteralContext ctx);
}