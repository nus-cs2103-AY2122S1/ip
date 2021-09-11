package duke.tasks;

/**
 * Class to encapsulate a ToDo
 */
public class ToDo extends Task {

    /**
     * Constructs a Event object.
     *
     * @param description ToDo description
     */
    public ToDo (String description) {
        super(description);
    }

    /**
     * Returns a string representing this object.
     *
     * @return String representing this object
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string used for saving a task.
     *
     * @return String for saving
     */
    @Override
    public String toSaveString() {
        return "| T" + " | " + super.toSaveString();
    }

    /**
     * Returns -1, 0, 1 to implement a comparison rank between Task objects.
     *
     * @param o Object to be compared to
     * @return integer 0 is equal, -1 is less and 1 is more
     */
    @Override
    public int compareTo(Task o) {
        if (!(o instanceof ToDo)) {
            return 1;
        }
        if (this.isDone ^ o.isDone) {
            return this.isDone ? 1 : -1;
        }
        return this.description.compareTo(o.description);
    }
}
