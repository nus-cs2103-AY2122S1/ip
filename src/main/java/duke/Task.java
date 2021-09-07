package duke;

/**
 * A class that represents a task
 *
 */
public class Task {
    private boolean done;
    private String title;

    /**
     * Constructor for Task class
     *
     * @param title Name of the task to be created
     */
    public Task(String title) {
        this.done = false;
        this.title = title;
    }

    @Override
    public String toString() {
        return (done ? "[X]" : "[ ]") + title;
    }

    /**
     * Method to mark a task as done.
     *
     */
    public void markAsDone() {
        done = true;
    }

    /**
     * Method to mark a task as done.
     *
     */
    public void markAsUndone() {
        done = false;
    }

    /**
     * A method that prints out details of a task.
     *
     * @return Details of task
     */
    public String writeTask() {
        String doneLabel = done ? "1" : "0";
        return doneLabel + " | " + title;
    }

    public String getTitle() {
        return title;
    }
}
