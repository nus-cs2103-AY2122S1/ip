package duke.task;

/**
 * Represents a task without any date or time attached to it.
 * E.g. walk the dog.
 */
public class Todo extends Task {

    /**
     * Class constructor.
     * @param description Description of the Todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns formatted string to write to the duke.txt file.
     *
     * @return String to write to duke.txt
     */
    @Override
    public String toWriteFormat() {
        String done = this.isDone ? "1" : "0";
        return String.format("T | %s | %s", done, this.getDescription());
    }


    /**
     * Returns the string representation of the Todo.
     *
     * @return String representation of Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Checks if the current task object is the same as a given task object.
     *
     * @param obj The given task object.
     * @return True if equals, False if not equals.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Todo other = (Todo) obj;
        if (!this.description.equals(other.getDescription())) {
            return false;
        }
        return true;
    }

    /** Returns a hash of the current object.
     *
     * @return The hash.
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.description != null ? this.description.hashCode() : 0);
        return hash;
    }
}
