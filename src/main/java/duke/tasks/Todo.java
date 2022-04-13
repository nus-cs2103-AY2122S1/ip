package duke.tasks;

/**
 * Todo tasks containing just a description and isDone field.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo Task given a description and uncompleted flag by default
     *
     * @param description Description for the todo task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a Todo Task, given both description and completion flag.
     *
     * @param description Description for the todo task
     * @param isDone Tracks whether task is done
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a letter identifying the Task as a Todo.
     *
     * @return A character identifying the Task
     */
    @Override
    public String getTaskIdentifier() {
        return "T";
    }

    /**
     * Returns a string representation of the Todo Task.
     *
     * @return String representing the Todo Task
     */
    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Does a deep comparison of this object to another object.
     *
     * @param otherObj The other object to be compared to
     * @return Returns true iff the two objects are of same type and same value in every field
     */
    @Override
    public boolean equals(Object otherObj) {
        if (!(otherObj instanceof Event)) {
            return false;
        } else {
            final Event otherEvent = (Event) otherObj;

            if (this.isDone != otherEvent.isDone) {
                return false;
            } else {
                return this.description.equals(otherEvent.description);
            }
        }
    }
}
