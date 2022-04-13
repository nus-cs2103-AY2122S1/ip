package src.main.java.duke;

/**
 * Class to represent a src.main.java.duke.Todo task.
 */


public class Todo extends Task {

    private String task;
    private boolean isDone;

    Todo(String t, boolean d) {
        super(t, d);
        task = t;
        isDone = d;
    }

    /**
     * overridden method to mark the task as done.
     */
    @Override
    void markAsDone() {
        this.isDone = true;
    }

    /**
     * overridden method to give the String representation of the task.
     *
     * @return
     */
    @Override
    public String toString() {
        return ("T | " + (this.isDone ? "1" : "0") + " | " + this.task);
    }
}
