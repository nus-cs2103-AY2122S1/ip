/**
 * Class to represent an event as a task
 */


public class Event extends Task {

    private String task;
    private boolean isDone;
    private String time;

    Event(String T, boolean D, String time) {
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
        return ("[E][" + (isDone ? "X" : " ") + "] " + task + " (at: " + this.time + ")");
    }
}
