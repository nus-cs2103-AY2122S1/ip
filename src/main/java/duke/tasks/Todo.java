package duke.tasks;

/** Class representing a todo */
public class Todo extends Task {
    public Todo(String taskDetails) {
        super(taskDetails);
    }

    /**
     * Returns a String representation of the Todo.
     *
     * @return String representation of the Todo.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Returns a String which is used to save data to disk.
     *
     * @return String representation of how data will be saved to disk.
     */
    @Override
    public String toDataString() {
        return String.format("T | %s", super.toDataString());
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj Object to compare to
     * @return boolean indicating whehter the other object is "equal" to this one.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Todo) {
            Todo tmp = (Todo) obj;
            return super.equals(obj);
        } else {
            return false;
        }
    }
}
