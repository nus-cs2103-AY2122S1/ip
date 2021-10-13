package duke;

/**
 * Represents a task in the list.
 */
public class Task {
    String name;
    boolean isDone;

    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void makeDone() {
        isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void makeUndone() {
        isDone = false;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return Indication of if the task is done or not followed by its name.
     */
    @Override
    public String toString() {
        if (isDone == true) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }

    /**
     * Returns true if the object being compared with is equivalent.
     *
     * @param obj Object to be compared with.
     * @return True if the object is a Task with the same name. False otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Task)) {
            return false;
        } else {
            Task objTask = (Task) obj;
            if (objTask.name.equals(this.name)) {
                return true;
            } else {
                return false;
            }
        }
    }

}
