package tokio.tasks;

import tokio.commands.Instruction;

import java.util.Locale;

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

    /**
     * Formats tasks for user display.
     *
     * @return Formatted todo task for user display.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Formats tasks for storage purposes.
     *
     * @return Formatted todo task for storage purposes.
     */
    @Override
    public String formatToStorage() {
        return "[T]" + super.formatToStorage();
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
            return this.description.toLowerCase(Locale.ROOT).equals(t.description);
        } else {
            return false;
        }
    }
}
