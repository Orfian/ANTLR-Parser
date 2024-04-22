package org.example.classes;

import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.example.lgBaseVisitor;
import org.example.lgParser;

import java.util.ArrayList;
import java.util.List;

public class CompilerVisitor extends lgBaseVisitor<List<Instruction>> {
    private final ParseTreeProperty<Type> Types;
    private int labelCounter = 0;
    public CompilerVisitor(ParseTreeProperty<Type> Types) {
        this.Types = Types;
    }

    @Override
    public List<Instruction> visitProgram(lgParser.ProgramContext ctx) {
        List<Instruction> instructions = new ArrayList<>();

        for (lgParser.StatementContext statement : ctx.statement()) {
            instructions.addAll(visit(statement));
        }

        return instructions;
    }

    @Override
    public List<Instruction> visitDeclarationStatement(lgParser.DeclarationStatementContext ctx) {
        List<Instruction> instructions = new ArrayList<>();

        Object defaultValue = null;
        Type type = Types.get(ctx.type());

        defaultValue = switch (type) {
            case INT -> "0";
            case FLOAT -> "0.0";
            case BOOL -> "false";
            case STRING -> "";
            default -> defaultValue;
        };

        for (TerminalNode id : ctx.ID()) {
            instructions.add(Instruction.builder()
                    .type(InstructionType.PUSH)
                    .value(new Object[]{Types.get(ctx.type()), defaultValue})
                    .build());

            instructions.add(Instruction.builder()
                    .type(InstructionType.SAVE)
                    .value(id.getText())
                    .build());
        };

        return instructions;
    }

    @Override
    public List<Instruction> visitReadStatement(lgParser.ReadStatementContext ctx) {
        List<Instruction> instructions = new ArrayList<>();

        for (TerminalNode id : ctx.ID()) {
            instructions.add(Instruction.builder()
                    .type(InstructionType.READ)
                    .value(Types.get(id))
                    .build());

            instructions.add(Instruction.builder()
                    .type(InstructionType.SAVE)
                    .value(id.getText())
                    .build());
        }

        return instructions;
    }

    @Override
    public List<Instruction> visitWriteStatement(lgParser.WriteStatementContext ctx) {
        List<Instruction> instructions = new ArrayList<>();

        for (lgParser.ExpressionContext expression : ctx.expression()) {
            instructions.addAll(visit(expression));
        }

        instructions.add(Instruction.builder()
                .type(InstructionType.PRINT)
                .value(ctx.expression().size())
                .build());

        return instructions;
    }

    @Override
    public List<Instruction> visitBlockStatement(lgParser.BlockStatementContext ctx) {
        List<Instruction> instructions = new ArrayList<>();

        for (lgParser.StatementContext statement : ctx.statement()) {
            instructions.addAll(visit(statement));
        }

        return instructions;
    }

    @Override
    public List<Instruction> visitIfStatement(lgParser.IfStatementContext ctx) {
        List<Instruction> instructions = new ArrayList<>();

        instructions.addAll(visit(ctx.expression()));

        if (ctx.statement().size() == 1) {
            int label = labelCounter++;
            instructions.add(Instruction.builder()
                    .type(InstructionType.FJMP)
                    .value(label)
                    .build());

            instructions.addAll(visit(ctx.statement(0)));

            instructions.add(Instruction.builder()
                    .type(InstructionType.LABEL)
                    .value(label)
                    .build());
        }
        else {
            int label1 = labelCounter++;
            instructions.add(Instruction.builder()
                    .type(InstructionType.FJMP)
                    .value(label1)
                    .build());

            instructions.addAll(visit(ctx.statement(0)));

            int label2 = labelCounter++;
            instructions.add(Instruction.builder()
                    .type(InstructionType.JMP)
                    .value(label2)
                    .build());

            instructions.add(Instruction.builder()
                    .type(InstructionType.LABEL)
                    .value(label1)
                    .build());

            instructions.addAll(visit(ctx.statement(1)));

            instructions.add(Instruction.builder()
                    .type(InstructionType.LABEL)
                    .value(label2)
                    .build());
        }

        return instructions;
    }

    @Override
    public List<Instruction> visitWhileStatement(lgParser.WhileStatementContext ctx) {
        List<Instruction> instructions = new ArrayList<>();

        int label1 = labelCounter++;
        instructions.add(Instruction.builder()
                .type(InstructionType.LABEL)
                .value(label1)
                .build());

        instructions.addAll(visit(ctx.expression()));

        int label2 = labelCounter++;
        instructions.add(Instruction.builder()
                .type(InstructionType.FJMP)
                .value(label2)
                .build());

        instructions.addAll(visit(ctx.statement()));

        instructions.add(Instruction.builder()
                .type(InstructionType.JMP)
                .value(label1)
                .build());

        instructions.add(Instruction.builder()
                .type(InstructionType.LABEL)
                .value(label2)
                .build());

        return instructions;
    }

    @Override
    public List<Instruction> visitDoWhileStatement(lgParser.DoWhileStatementContext ctx) {
        List<Instruction> instructions = new ArrayList<>();

        int label1 = labelCounter++;
        instructions.add(Instruction.builder()
                .type(InstructionType.LABEL)
                .value(label1)
                .build());

        instructions.addAll(visit(ctx.statement()));

        instructions.addAll(visit(ctx.expression()));

        int label2 = labelCounter++;
        instructions.add(Instruction.builder()
                .type(InstructionType.FJMP)
                .value(label2)
                .build());

        instructions.add(Instruction.builder()
                .type(InstructionType.JMP)
                .value(label1)
                .build());

        instructions.add(Instruction.builder()
                .type(InstructionType.LABEL)
                .value(label2)
                .build());

        return instructions;
    }

    @Override
    public List<Instruction> visitExpressionStatement(lgParser.ExpressionStatementContext ctx) {
        List<Instruction> instructions = new ArrayList<>();

        instructions.addAll(visit(ctx.expression()));

        instructions.add(Instruction.builder()
                .type(InstructionType.POP)
                .build());

        return instructions;
    }

    @Override
    public List<Instruction> visitParentheses(lgParser.ParenthesesContext ctx) {
        List<Instruction> instructions = new ArrayList<>();

        instructions.addAll(visit(ctx.expression()));

        return instructions;
    }

    @Override
    public List<Instruction> visitIdentifier(lgParser.IdentifierContext ctx) {
        List<Instruction> instructions = new ArrayList<>();

        instructions.add(Instruction.builder()
                .type(InstructionType.LOAD)
                .value(ctx.ID().getText())
                .build());

        return instructions;
    }

    @Override
    public List<Instruction> visitLogicalNot(lgParser.LogicalNotContext ctx) {
        List<Instruction> instructions = new ArrayList<>();

        instructions.addAll(visit(ctx.expression()));

        instructions.add(Instruction.builder()
                .type(InstructionType.NOT)
                .build());

        return instructions;
    }

    @Override
    public List<Instruction> visitConcatenation(lgParser.ConcatenationContext ctx) {
        List<Instruction> instructions = new ArrayList<>();

        instructions.addAll(visit(ctx.expression(0)));

        instructions.addAll(visit(ctx.expression(1)));

        instructions.add(Instruction.builder()
                .type(InstructionType.CONCAT)
                .build());

        return instructions;
    }

    @Override
    public List<Instruction> visitAssignment(lgParser.AssignmentContext ctx) {
        List<Instruction> instructions = new ArrayList<>();

        instructions.addAll(visit(ctx.expression()));

        Type leftType = Types.get(ctx.ID());
        Type rightType = Types.get(ctx.expression());

        if (leftType != rightType) {
            instructions.add(Instruction.builder().type(InstructionType.ITOF).build());
        }

        instructions.add(Instruction.builder()
                .type(InstructionType.SAVE)
                .value(ctx.ID().getText())
                .build());

        instructions.add(Instruction.builder()
                .type(InstructionType.LOAD)
                .value(ctx.ID().getText())
                .build());

        return instructions;
    }

    @Override
    public List<Instruction> visitUnaryMinus(lgParser.UnaryMinusContext ctx) {
        List<Instruction> instructions = new ArrayList<>();

        instructions.addAll(visit(ctx.expression()));
        instructions.add(Instruction.builder().type(InstructionType.UMINUS).build());

        return instructions;
    }

    @Override
    public List<Instruction> visitVariable(lgParser.VariableContext ctx) {
        List<Instruction> instructions = new ArrayList<>();

        instructions.add(Instruction.builder()
                .type(InstructionType.PUSH)
                .value(new Object[]{Types.get(ctx.literal()), ctx.literal().getText()})
                .build());

        return instructions;
    }

    @Override
    public List<Instruction> visitArithmetic(lgParser.ArithmeticContext ctx) {
        List<Instruction> instructions = new ArrayList<>();

        instructions.addAll(visit(ctx.expression(0)));

        Type leftType = Types.get(ctx.expression(0));
        Type rightType = Types.get(ctx.expression(1));

        if (leftType == Type.INT && rightType == Type.FLOAT) {
            instructions.add(Instruction.builder().type(InstructionType.ITOF).build());
            leftType = Type.FLOAT;
        }

        instructions.addAll(visit(ctx.expression(1)));

        if (leftType == Type.FLOAT && rightType == Type.INT) {
            instructions.add(Instruction.builder().type(InstructionType.ITOF).build());
        }

        switch (ctx.op.getText()) {
            case "+":
                instructions.add(Instruction.builder().type(InstructionType.ADD).value(leftType).build());
                break;
            case "-":
                instructions.add(Instruction.builder().type(InstructionType.SUB).value(leftType).build());
                break;
            case "*":
                instructions.add(Instruction.builder().type(InstructionType.MUL).value(leftType).build());
                break;
            case "/":
                instructions.add(Instruction.builder().type(InstructionType.DIV).value(leftType).build());
                break;
            case "%":
                instructions.add(Instruction.builder().type(InstructionType.MOD).value(leftType).build());
                break;
        }

        return instructions;
    }

    @Override
    public List<Instruction> visitRelational(lgParser.RelationalContext ctx) {
        List<Instruction> instructions = new ArrayList<>();

        instructions.addAll(visit(ctx.expression(0)));

        Type leftType = Types.get(ctx.expression(0));
        Type rightType = Types.get(ctx.expression(1));

        if (leftType == Type.INT && rightType == Type.FLOAT) {
            instructions.add(Instruction.builder().type(InstructionType.ITOF).build());
        }

        instructions.addAll(visit(ctx.expression(1)));

        if (leftType == Type.FLOAT && rightType == Type.INT) {
            instructions.add(Instruction.builder().type(InstructionType.ITOF).build());
        }

        switch (ctx.op.getText()) {
            case "<":
                instructions.add(Instruction.builder().type(InstructionType.LT).build());
                break;
            case ">":
                instructions.add(Instruction.builder().type(InstructionType.GT).build());
                break;
        }

        return instructions;
    }

    @Override
    public List<Instruction> visitEquality(lgParser.EqualityContext ctx) {
        List<Instruction> instructions = new ArrayList<>();

        instructions.addAll(visit(ctx.expression(0)));

        Type leftType = Types.get(ctx.expression(0));
        Type rightType = Types.get(ctx.expression(1));

        if (leftType == Type.INT && rightType == Type.FLOAT) {
            instructions.add(Instruction.builder().type(InstructionType.ITOF).build());
        }

        instructions.addAll(visit(ctx.expression(1)));

        if (leftType == Type.FLOAT && rightType == Type.INT) {
            instructions.add(Instruction.builder().type(InstructionType.ITOF).build());
        }

        switch (ctx.op.getText()) {
            case "==":
                instructions.add(Instruction.builder().type(InstructionType.EQ).build());
                break;
            case "!=":
                instructions.add(Instruction.builder().type(InstructionType.EQ).build());
                instructions.add(Instruction.builder().type(InstructionType.NOT).build());
                break;
        }

        return instructions;
    }

    @Override
    public List<Instruction> visitLogical(lgParser.LogicalContext ctx) {
        List<Instruction> instructions = new ArrayList<>();

        instructions.addAll(visit(ctx.expression(0)));

        Type leftType = Types.get(ctx.expression(0));
        Type rightType = Types.get(ctx.expression(1));

        if (leftType == Type.INT && rightType == Type.FLOAT) {
            instructions.add(Instruction.builder().type(InstructionType.ITOF).build());
        }

        instructions.addAll(visit(ctx.expression(1)));

        if (leftType == Type.FLOAT && rightType == Type.INT) {
            instructions.add(Instruction.builder().type(InstructionType.ITOF).build());
        }

        switch (ctx.op.getText()) {
            case "&&":
                instructions.add(Instruction.builder().type(InstructionType.AND).build());
                break;
            case "||":
                instructions.add(Instruction.builder().type(InstructionType.OR).build());
                break;
        }

        return instructions;
    }

    @Override
    public List<Instruction> visitLiteral(lgParser.LiteralContext ctx) {
        List<Instruction> instructions = new ArrayList<>();

        Object value = switch (ctx.getStart().getType()) {
            case lgParser.INT -> Integer.parseInt(ctx.getText());
            case lgParser.FLOAT -> Float.parseFloat(ctx.getText());
            case lgParser.STRING -> ctx.getText().substring(1, ctx.getText().length() - 1);
            case lgParser.BOOL -> Boolean.parseBoolean(ctx.getText());
            default -> null;
        };

        instructions.add(Instruction.builder()
                .type(InstructionType.PUSH)
                .value(new Object[]{Types.get(ctx), value})
                .build());

        return instructions;
    }
}
