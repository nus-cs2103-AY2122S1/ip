package duke.task;

/**
 * The class to represent a todo.
 */
public class ToDo extends Task {

    /**
     * Constructor of ToDo class.
     *
     * @param name name of task
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Constructor of ToDo class.
     *
     * @param name name of task
     * @param isDone whether the task has been completed
     */
    public ToDo(String name, boolean isDone) {
        super(name, isDone);
    }

    /**
     * Returns the string representation of the ToDo instance.
     *
     * @return the string representation of the ToDo instance
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string to be recorded in the data file of the ToDo instance.
     *
     * @return the string to be recorded of the ToDo instance
     */
    @Override
    public String getRecordString() {
        int done = this.isDone ? 1 : 0;
        String record = String.format("T | %d | %s", done, this.name);
        return record;
    }

    /**
     * Determines if two instances of ToDo are equal.
     *
     * @param obj the object to be used for comparison
     * @return boolean indicating if the two ToDo instances are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ToDo) {
            ToDo td = (ToDo) obj;
            return this.name.equals(td.name) && this.isDone == td.isDone;
        }
        return false;
    }
}
