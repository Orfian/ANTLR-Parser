package org.example.classes;

import java.util.*;

public class Processor {
    private final Stack<Object> stack = new Stack<>();
    private final Map<String, Object> variables = new HashMap<>();
    private final Map<String, Integer> labels = new HashMap<>();
    private final Scanner scanner;
    int instructionPointer = 0;

    public Processor(List<Instruction> instructions, Scanner scanner) {
        this.scanner = scanner;
        for (int i = 0; i < instructions.size(); i++) {
            if (instructions.get(i).getType() == InstructionType.LABEL) {
                labels.put(instructions.get(i).getValue().toString(), i);
            }
        }

        while (instructionPointer < instructions.size()) {
            Instruction ins = instructions.get(instructionPointer);
            execute(ins);
            instructionPointer++;
        }
    }

    public void execute(Instruction ins) {
        switch (ins.getType()) {
            case ADD -> {
                Object a = stack.pop();
                Object b = stack.pop();

                switch (ins.getValue().toString()) {
                    case "INT" -> stack.push((int) b + (int) a);
                    case "FLOAT" -> stack.push((float) b + (float) a);
                }
            }
            case SUB -> {
                Object a = stack.pop();
                Object b = stack.pop();

                switch (ins.getValue().toString()) {
                    case "INT" -> stack.push((int) b - (int) a);
                    case "FLOAT" -> stack.push((float) b - (float) a);
                }
            }
            case MUL -> {
                Object a = stack.pop();
                Object b = stack.pop();

                switch (ins.getValue().toString()) {
                    case "INT" -> stack.push((int) b * (int) a);
                    case "FLOAT" -> stack.push((float) b * (float) a);
                }
            }
            case DIV -> {
                Object a = stack.pop();
                Object b = stack.pop();

                switch (ins.getValue().toString()) {
                    case "INT" -> stack.push((int) b / (int) a);
                    case "FLOAT" -> stack.push((float) b / (float) a);
                }
            }
            case MOD -> {
                Object a = stack.pop();
                Object b = stack.pop();

                switch (ins.getValue().toString()) {
                    case "INT" -> stack.push((int) b % (int) a);
                    case "FLOAT" -> stack.push((float) b % (float) a);
                }
            }
            case UMINUS -> {
                Object a = stack.pop();

                if (a instanceof Integer) {
                    stack.push(-(int) a);
                } else if (a instanceof Float) {
                    stack.push(-(float) a);
                } else {
                    throw new RuntimeException("Invalid operand for UMINUS operation.");
                }
            }
            case CONCAT -> {
                Object a = stack.pop();
                Object b = stack.pop();

                if (a instanceof String && b instanceof String) {
                    stack.push((String) b + (String) a);
                } else {
                    throw new RuntimeException("Invalid operands for CONCAT operation.");
                }
            }
            case AND -> {
                Object a = stack.pop();
                Object b = stack.pop();

                if (a instanceof Boolean && b instanceof Boolean) {
                    stack.push((boolean) b && (boolean) a);
                } else {
                    throw new RuntimeException("Invalid operands for AND operation.");
                }
            }
            case OR -> {
                Object a = stack.pop();
                Object b = stack.pop();

                if (a instanceof Boolean && b instanceof Boolean) {
                    stack.push((boolean) b || (boolean) a);
                } else {
                    throw new RuntimeException("Invalid operands for OR operation.");
                }
            }
            case GT -> {
                Object a = stack.pop();
                Object b = stack.pop();

                if (a instanceof Integer && b instanceof Integer) {
                    stack.push((int) b > (int) a);
                } else if (a instanceof Float && b instanceof Float) {
                    stack.push((float) b > (float) a);
                } else {
                    throw new RuntimeException("Invalid operands for GT operation.");
                }
            }
            case LT -> {
                Object a = stack.pop();
                Object b = stack.pop();

                if (a instanceof Integer && b instanceof Integer) {
                    stack.push((int) b < (int) a);
                } else if (a instanceof Float && b instanceof Float) {
                    stack.push((float) b < (float) a);
                } else {
                    throw new RuntimeException("Invalid operands for LT operation.");
                }
            }
            case EQ -> {
                Object a = stack.pop();
                Object b = stack.pop();

                if (a instanceof Integer && b instanceof Integer) {
                    stack.push((int) b == (int) a);
                } else if (a instanceof Float && b instanceof Float) {
                    stack.push((float) b == (float) a);
                } else if (a instanceof Boolean && b instanceof Boolean) {
                    stack.push((boolean) b == (boolean) a);
                } else {
                    throw new RuntimeException("Invalid operands for EQ operation.");
                }
            }
            case NOT -> {
                Object a = stack.pop();

                if (a instanceof Boolean) {
                    stack.push(!(boolean) a);
                } else {
                    throw new RuntimeException("Invalid operand for NOT operation.");
                }
            }
            case ITOF -> {
                Object a = stack.pop();

                if (a instanceof Integer) {
                    stack.push((float) (int) a);
                } else {
                    throw new RuntimeException("Invalid operand for ITOF operation.");
                }
            }
            case PUSH -> {
                switch (((Object[]) ins.getValue())[0])
                {
                    case Type.INT -> stack.push(toInt(ins.getValue()));
                    case Type.FLOAT -> stack.push(toFloat(ins.getValue()));
                    case Type.STRING -> stack.push(toStr(ins.getValue()));
                    case Type.BOOL -> stack.push(toBool(ins.getValue()));
                    default -> throw new RuntimeException("Invalid type.");
                }
            }
            case POP -> {
                stack.pop();
            }
            case LOAD -> {
                stack.push(variables.get(ins.getValue()));
            }
            case SAVE -> {
                variables.put((String) ins.getValue(), stack.pop());
            }
            case JMP -> {
                instructionPointer = labels.get(ins.getValue());
            }
            case FJMP -> {
                if (!(boolean) stack.pop()) {
                    instructionPointer = labels.get(ins.getValue());
                }
            }
            case PRINT -> {
                List<String> values = new ArrayList<>();
                for (int i = 0; i < (int) ins.getValue(); i++) {
                    values.add(stack.pop().toString());
                }

                for (int i = values.size() - 1; i >= 0; i--) {
                    System.out.println(values.get(i));
                }
            }
            case READ -> {
                String type = ins.getValue().toString();

                switch (type) {
                    case "INT" -> {
                        System.out.print("INT: ");
                        stack.push(Integer.parseInt(scanner.nextLine()));
                    }
                    case "FLOAT" -> {
                        System.out.print("FLOAT: ");
                        stack.push(Float.parseFloat(scanner.nextLine()));
                    }
                    case "STRING" -> {
                        System.out.print("STRING: ");
                        stack.push(scanner.nextLine());
                    }
                    case "BOOL" -> {
                        System.out.print("BOOL: ");
                        stack.push(Boolean.parseBoolean(scanner.nextLine()));
                    }
                }
            }
        }
    }

    public int toInt(Object value) {
        return Integer.parseInt((String) ((Object[]) value)[1]);
    }

    public float toFloat(Object value) {
        return Float.parseFloat((String) ((Object[]) value)[1]);
    }

    public String toStr(Object value) {
        return (String) ((Object[]) value)[1];
    }

    public boolean toBool(Object value) {
        return Boolean.parseBoolean((String) ((Object[]) value)[1]);
    }
}
