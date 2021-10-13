package duke;

/**
 * Represents a to-do type of task in the list.
 */
public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    /**
     * Returns the string representation of the task.
     *
     * @return Indication that the task is a to-do type and if the task
     * is done or not followed by its name.
     */
    @Override
    public String toString() {
        if (isDone == true) {
            return "[T][X] " + name;
        } else {
            return "[T][ ] " + name;
        }
    }

    /**
     * Returns true if the object being compared with is equivalent.
     *
     * @param obj Object to be compared with.
     * @return True if the object is a ToDo with the same name. False otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ToDo)) {
            return false;
        } else {
            ToDo objTask = (ToDo) obj;
            if (objTask.name.equals(this.name)) {
                return true;
            } else {
                return false;
            }
        }
    }
}
