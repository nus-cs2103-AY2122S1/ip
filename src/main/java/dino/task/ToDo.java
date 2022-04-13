package dino.task;

/**
 * Tasks without any date/time attached to it e.g., visit new theme park
 * It is a subclass of Task
 */
public class ToDo extends Task {

    /**
     * Constructs a todo object
     *
     * @param description the description for the todo task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Displays the todo task in the format of "T | Status | Description"
     * @return the todo task in the required format
     */
    @Override
    public String toString() {
        return "T" + super.toString();
    }
}

