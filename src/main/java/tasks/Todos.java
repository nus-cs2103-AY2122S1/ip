package tasks;

import viper.Instruction;

/**
 * Represents a todo task.
 */
public class Todos extends Task {

    public Todos(String description) {
        super(description, Instruction.TODO);
    }
    
    public Instruction taskType() {
        return Instruction.TODO;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
