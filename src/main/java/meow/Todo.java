package meow;

/**
 * Represents a specific task (a todo) containing the description of the task
 * that the user wants to add in his or her todo list.
 */
public class Todo extends Task {

    /**
     * A public constructor to initialize a meow.Todo object.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the meow.Todo object.
     *
     * @return The string representation of the meow.Todo object.
     */
    @Override
    public String toString() {
        return "T" + super.toString();
    }
}
