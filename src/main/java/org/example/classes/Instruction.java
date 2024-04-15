package org.example.classes;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Instruction {
    private InstructionType type;
    private Object value;

    @Override
    public String toString() {
        if (value == null) {
            return type.toString();
        }

        if (value instanceof Object[]) {
            Object[] array = (Object[]) value;
            StringBuilder sb = new StringBuilder();
            sb.append(array[0]);
            sb.append(" ");
            sb.append(array[1]);
            return type + " \t" + sb.toString();
        }

        return type + " \t" + value;
    }
}
