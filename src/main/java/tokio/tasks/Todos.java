package tokio.tasks;

import tokio.commands.Instruction;

/**
 * Represents a todo task.
 */
public class Todos extends Task {
    protected String description;
    
    /**
     * Constructor for Todo with description provided by user.
     *
     * @param description Description of Todo item.
     */
    public Todos(String description) {
        super(description, Instruction.TODO);
        this.description = description;
    }
    
    
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Compares two objects, if both objects are Todos and have the same name,
     * then they will be considered equal.
     *
     * @param obj Object to be compared to.
     * @return True is objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Todos) {
            Todos t = (Todos) obj;
            return this.description.equals(t.description);
        } else {
            return false;
        }
    }
}
