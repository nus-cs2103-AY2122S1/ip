package duke.task;

public class Event extends Task {
    private String taskType = "[E]";

    /**
     * Initialises an Event object.
     *
     * @param taskName the task name
     * @param eventTime the event time
     */
    public Event(String taskName, String eventTime) {
        super(taskName, eventTime);
    }

    /**
     * Initialises an Event object with its status.
     *
     * @param taskName the task name
     * @param eventTime the event time
     * @param isDone the status of the event task
     */
    public Event(String taskName, String eventTime, boolean isDone) {
        super(taskName, eventTime);
        if (isDone) {
            this.markAsDone();
        }
    }

    public String getEventTime() {
        return String.format("(at: %s)", this.getDateAndTime());
    }

    @Override
    public String toString() {
        String result = taskType + this.getCheckBox()
                + " " + this.getTaskName() + " "
                + getEventTime();
        return result;
    }
}
