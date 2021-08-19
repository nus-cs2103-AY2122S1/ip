/**
 * Class to represent a task as a with a deadline
 */


public class Deadline extends Task {

    private String task;
    private boolean isDone;
    private String time;

    Deadline(String T, boolean D, String time) {
        super(T, D);
        task = T;
        isDone = D;
        this.time = time;
    }

    /**
     * overridden method to mark the task as done
     */
    @Override
    void markAsDone() {
        this.isDone = true;
    }

    /**
     * overridden method to give the String representation of the task
     *
     * @return
     */
    @Override
    public String toString() {
        return ("[D][" + (isDone ? "X" : " ") + "] " + task + " (by: " + this.time + ")");
    }
}
