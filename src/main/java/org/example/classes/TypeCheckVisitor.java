package org.example.classes;
import org.example.*;

public class TypeCheckVisitor extends lgBaseVisitor<Type> {
    private final SymbolTable SymbolTable = new SymbolTable();

    @Override
    public Type visitIfStatement(lgParser.IfStatementContext ctx) {
        if ((Type) visit(ctx.expression()) != Type.BOOL) {
            Errors.addError(ctx.start, "If statement condition must be of type bool.");
            return Type.ERROR;
        }

        for (lgParser.StatementContext statement : ctx.statement()) {
            visit(statement);
        }

        return null;
    }

    @Override
    public Type visitWhileStatement(lgParser.WhileStatementContext ctx) {
        if ((Type) visit(ctx.expression()) != Type.BOOL) {
            Errors.addError(ctx.start, "While statement condition must be of type bool.");
            return Type.ERROR;
        }

        visit(ctx.statement());

        return null;
    }

    @Override
    public Type visitDoWhileStatement(lgParser.DoWhileStatementContext ctx) {
        if ((Type) visit(ctx.expression()) != Type.BOOL) {
            Errors.addError(ctx.start, "Do-while statement condition must be of type bool.");
            return Type.ERROR;
        }

        visit(ctx.statement());

        return null;
    }

    @Override
    public Type visitParentheses(lgParser.ParenthesesContext ctx) {
        return visit(ctx.expression());
    }

    @Override
    public Type visitIdentifier(lgParser.IdentifierContext ctx) {
        return SymbolTable.getSymbol(ctx.ID().getSymbol());
    }

    @Override
    public Type visitLogicalNot(lgParser.LogicalNotContext ctx) {
        if ((Type) visit(ctx.expression()) != Type.BOOL) {
            Errors.addError(ctx.start, "Logical not expression must be of type bool.");
            return Type.ERROR;
        }

        return Type.BOOL;
    }

    @Override
    public Type visitConcatenation(lgParser.ConcatenationContext ctx) {
        if ((Type) visit(ctx.expression(0)) != Type.STRING || (Type) visit(ctx.expression(1)) != Type.STRING) {
            Errors.addError(ctx.start, "Concatenation expressions must be of type string.");
            return Type.ERROR;
        }

        return Type.STRING;
    }

    @Override
    public Type visitAssignment(lgParser.AssignmentContext ctx) {
        Type leftType = SymbolTable.getSymbol(ctx.ID().getSymbol());
        Type rightType = (Type) visit(ctx.expression());

        if (leftType == Type.ERROR || rightType == Type.ERROR) {
            return Type.ERROR;
        }

        if ((leftType == Type.INT && rightType == Type.FLOAT) || (leftType == Type.FLOAT && rightType == Type.INT) || (leftType == Type.FLOAT && rightType == Type.FLOAT)) {
            return Type.FLOAT;
        }

        if (leftType != rightType) {
            Errors.addError(ctx.start, "Assignment expressions must be of the same type.");
            return Type.ERROR;
        }

        return leftType;
    }

    @Override
    public Type visitUnaryMinus(lgParser.UnaryMinusContext ctx) {
        if ((Type) visit(ctx.expression()) == Type.INT) {
            return Type.INT;
        }
        else if ((Type) visit(ctx.expression()) == Type.FLOAT) {
            return Type.FLOAT;
        }
        else {
            Errors.addError(ctx.start, "Unary minus expression must be of type int or float.");
            return Type.ERROR;
        }
    }

    @Override
    public Type visitArithmetic(lgParser.ArithmeticContext ctx) {
        Type leftType = (Type) visit(ctx.expression(0));
        Type rightType = (Type) visit(ctx.expression(1));

        if (ctx.op.getText().equals("%") && (leftType != Type.INT || rightType != Type.INT)) {
            Errors.addError(ctx.start, "Modulo expressions must be of type int.");
            return Type.ERROR;
        }

        if (leftType == Type.INT && rightType == Type.INT) {
            return Type.INT;
        }
        else if ((leftType == Type.INT && rightType == Type.FLOAT) || (leftType == Type.FLOAT && rightType == Type.INT) || (leftType == Type.FLOAT && rightType == Type.FLOAT)) {
            return Type.FLOAT;
        }
        else {
            Errors.addError(ctx.start, "Arithmetic expressions must be of type int or float.");
            return Type.ERROR;
        }
    }

    @Override
    public Type visitRelational(lgParser.RelationalContext ctx) {
        Type leftType = (Type) visit(ctx.expression(0));
        Type rightType = (Type) visit(ctx.expression(1));

        if (leftType == Type.INT && rightType == Type.INT) {
            return Type.BOOL;
        }
        else if ((leftType == Type.INT && rightType == Type.FLOAT) || (leftType == Type.FLOAT && rightType == Type.INT) || (leftType == Type.FLOAT && rightType == Type.FLOAT)) {
            return Type.BOOL;
        }
        else {
            Errors.addError(ctx.start, "Relational expressions must be of type int or float.");
            return Type.ERROR;
        }
    }

    @Override
    public Type visitEquality(lgParser.EqualityContext ctx) {
        Type leftType = (Type) visit(ctx.expression(0));
        Type rightType = (Type) visit(ctx.expression(1));

        if (leftType == rightType) {
            return Type.BOOL;
        }
        else {
            Errors.addError(ctx.start, "Equality expressions must be of the same type.");
            return Type.ERROR;
        }
    }

    @Override
    public Type visitLogical(lgParser.LogicalContext ctx) {
        Type leftType = (Type) visit(ctx.expression(0));
        Type rightType = (Type) visit(ctx.expression(1));

        if (leftType == Type.BOOL && rightType == Type.BOOL) {
            return Type.BOOL;
        }
        else {
            Errors.addError(ctx.start, "Logical expressions must be of type bool.");
            return Type.ERROR;
        }
    }

    @Override
    public Type visitType(lgParser.TypeContext ctx) {
        return switch (ctx.getText()) {
            case "int" -> Type.INT;
            case "float" -> Type.FLOAT;
            case "string" -> Type.STRING;
            case "bool" -> Type.BOOL;
            default -> {
                Errors.addError(ctx.start, "Invalid type: " + ctx.getText());
                yield Type.ERROR;
            }
        };
    }

    @Override
    public Type visitLiteral(lgParser.LiteralContext ctx) {
        return switch (ctx.getStart().getType()) {
            case lgParser.INT -> Type.INT;
            case lgParser.FLOAT -> Type.FLOAT;
            case lgParser.STRING -> Type.STRING;
            case lgParser.BOOL -> Type.BOOL;
            default -> Type.ERROR;
        };
    }
}
