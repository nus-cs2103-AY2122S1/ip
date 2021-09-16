package src.main.java.duke;

/**
 * Class to represent an event as a task.
 */


public class Event extends Task {

    private String task;
    private boolean isDone;
    private String time;

    Event(String t, boolean d, String time) {
        super(t, d);
        task = t;
        isDone = d;
        this.time = time;
    }

    /**
     * overridden method to mark the task as done.
     */
    @Override
    void markAsDone() {
        this.isDone = true;
    }

    /**
     * method to update the time of the Event to the specified time in String.
     *
     * @param time time in String to be set for the event.
     */
    @Override
    void setTime(String time) throws DukeException {
        this.time = time;
    }

    /**
     * overridden method to give the String representation of the task.
     *
     * @return String representation of Event.
     */
    @Override
    public String toString() {
        return ("E | " + (isDone ? "1" : "0") + " | " + this.task + " | " + this.time);
    }
}
