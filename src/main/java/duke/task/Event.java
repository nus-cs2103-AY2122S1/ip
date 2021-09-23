package duke.task;

/**
 * Encapsulates an event.
 */
public class Event extends Task {
    private String taskName;
    private String time;
    private String taskSymbol = "E";

    /**
     * Constructs an event object.
     * @param taskName Name of the task.
     * @param time The time that the event occurs.
     */
    public Event(String taskName, String time) {
        this.taskName = taskName;
        this.time = time;
    }

    @Override
    public String toString() {
        String statusIcon = getStatusIcon();
        return String.format("[%s][%s] %s (at: %s)", taskSymbol, statusIcon, taskName, formatTime(time));
    }

    @Override
    public String toStorageFormat() {
        return String.format("%s/%s/%s/%s", taskSymbol, isCompleted(), taskName, time);
    }
}


