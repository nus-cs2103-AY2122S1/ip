package duke;

/**
 * Todo class is a task. The input must be in such a format
 * "todo <todo name>".
 */
public class ToDo extends Task {
    protected String description;
    protected boolean isDone;
    private final String TODO = "[T]";

    /**
     * Public constructor for a todo.
     *
     * @param description Description of the event.
     */
    public ToDo(String description) {
        super(description);
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the string of the todo.
     *
     * @return String of event.
     */
    @Override
    public String toString() {
        return TODO + this.getStatusIcon() + " " + this.getDescription();
    }

    /**
     * Checks to see if two todos are equal in description and status.
     * Returns false if object is not equal to this todo.
     *
     * @param object Object compared to.
     * @return Boolean true if equal, false otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof ToDo) {
            ToDo toDo = (ToDo) object;
            return toDo.getDone() == this.getDone() && toDo.description.equals(this.description);
        }
        return false;
    }
}
