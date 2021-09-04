package duke.tasks;

/**
 * ToDo class used to represent a task that has no fixed date.
 * Contains method that
 * (i) overrides the Parent toString method to display the task type,
 * as well as status and description.
 */
public class ToDo extends Task {
    /**
     * Creates a new ToDo object.
     *
     * @param description String object representing the task being input into ToDo.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Creates a new ToDo object. (Overloaded constructor)
     *
     * @param done String object representing whether the task is completed.
     * @param description String object representing the task being input into ToDo.
     */
    public ToDo(String done, String description) {
        super(description);
        if ((done.equals("X"))) {
            this.setIsDone(true);
        } else {
            this.setIsDone(false);
        }
    }

    /**
     * Returns formatted String representation of a ToDo task.
     *
     * @return String object to represent ToDo task.
     */
    @Override
    public String formatString() {
        return toString();
    }

    /**
     * Returns String representation of ToDo task.
     *
     * @return String type object that includes the task type and parent toString() method.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
