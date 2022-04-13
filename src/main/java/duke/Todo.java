package duke;

/**
 * This class extends Task.
 */
public class Todo extends Task {

    /**
     * Constructor for this class. This handles user input.
     *
     * @param input Raw input from the user.
     */
    public Todo(String input) {
        super(input.substring(5));
    }

    /**
     * toString method for this class.
     *
     * @return Displays the task as [T].
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
