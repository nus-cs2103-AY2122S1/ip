package duke.tasks;

/**
 * Todo class inherits from Task that contains the name, the time and the completion status.
 */
public class Todo extends Task {

    private String marker = "T";
    private String time = "10/10/1999 1010";

    /**
     * Constructor for a Todo.
     * @param name Name of the Todo.
     * @param isDone Completion status of the Todo.
     */
    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    /**
     * Returns the time in a specified format.
     * @return Time in dd/MM/yy HHmm.
     */
    @Override
    public String getTime() {
        return this.time;
    }

    /**
     * Returns the marker "E" denoting event.
     * @return A string "E".
     */
    @Override
    public String getMarker() {
        return this.marker;
    }

    /**
     * The string representation of an Event.
     * @return Event in string.
     */
    @Override
    public String toString() {
        return "[" + this.marker + "]" + super.toString();
    }
}
