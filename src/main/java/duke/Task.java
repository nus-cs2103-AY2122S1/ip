package duke;

/**
 * Task is a class that provides the basic methods and fields that all other variations
 * such as deadline and event will use.
 *
 * @author meerian
 */
public class Task {
    private boolean isDone = false;
    private final String name;

    /**
     * Creates a task with the specified description.
     *
     * @param name The task's description.
     * @throws DukeException when the user inputs an empty description.
     */
    public Task(String name) throws DukeException {
        if (name.isEmpty()) {
            throw new DukeException(Ui.wrongFormat());
        }
        this.name = name;
    }

    /**
     * Sets the task as done.
     */
    public void done() {
        this.isDone = true;
    }

    /**
     * Returns the string representation of the task to be displayed.
     *
     * @return the string representation of the task.
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }
}
