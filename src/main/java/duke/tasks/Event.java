package duke.tasks;

import duke.functionality.Datetime;

/**
 * Represents an Event task.
 */
public class Event extends Task {
    private static final String TASK_TAG = "event";
    private final Datetime eventDatetime;

    /**
     * Returns a new Event task with specified name and due date.
     *
     * @param taskName The name of the task.
     * @param eventDatetime The date on which the event is occuring.
     */
    public Event(String taskName, String eventDatetime) {
        super(taskName);
        this.eventDatetime = new Datetime(eventDatetime);
    }

    /**
     * Returns a new Event task with specified name, due date, and completion status.
     *
     * @param taskName The name of the task.
     * @param eventDatetime The date on which the event is occurring.
     * @param isDone Completion status of the task.
     */
    public Event(String taskName, String eventDatetime, boolean isDone) {
        super(taskName, isDone);
        this.eventDatetime = new Datetime(eventDatetime);
    }

    /**
     * Returns the string format in which this task is to be saved within a file.
     *
     * @return String representation of task for saving within a file.
     */
    public String fileSaveFormat() {
        return String.format("E | %d | %s | %s", this.isDone() ? 1 : 0, this.taskName(),
                this.eventDatetime.getDatetimeString());
    }

    public static String taskTag() {
        return TASK_TAG;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.eventDatetime.toString() + ")";
    }
}
