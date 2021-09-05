package tokio.tasks;

import tokio.commands.Instruction;

/**
 * Represents a todo task.
 */
public class Todos extends Task {

    /**
     * Constructor for Todo with description provided by user.
     *
     * @param description Description of Todo item.
     */
    public Todos(String description) {
        super(description, Instruction.TODO);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
