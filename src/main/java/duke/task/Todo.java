package duke.task;

/**
 * Encapsulates the idea of tasks
 */
public class Todo extends Task {

    // constants

    private static final String TYPE = "T";

    // constructor

    /**
     * Constructor for a Todo
     *
     * @param description the description of the Todo
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructor for a Todo and whether it is completed
     *
     * @param description the description of the Todo
     * @param isDone      whether the Event is completed
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Getter method to get the String representation
     * of the type of the Todo object
     *
     * @return the type of Todo
     */
    public String getLabel() {
        return TYPE;
    }

    /**
     * A method to convert and format a Todo object
     * into a string to be stored in the database
     *
     * @return the formatted string representation
     */
    @Override
    public String databaseString() {
        return TYPE + " | " + super.databaseString();
    }

    /**
     * A method to convert a Todo object into a string representation
     *
     * @return the string representation of an Event
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", getLabel(), getStatusIcon(), this.description);
    }
}
