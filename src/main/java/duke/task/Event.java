package duke.task;

public class Event extends Task {
    private String taskType = "[E]";
    private String taskName;
    private String eventTime;

    /**
     * Initialises an Event object.
     *
     * @param taskName the task name
     * @param eventTime the event time
     */
    public Event(String taskName, String eventTime) {
        super(taskName);
        this.taskName = taskName;
        this.eventTime = eventTime;
    }

    /**
     * Initialises an Event object with its status.
     *
     * @param taskName the task name
     * @param eventTime the event time
     * @param isDone the status of the event task
     */
    public Event(String taskName, String eventTime, boolean isDone) {
        super(taskName);
        this.taskName = taskName;
        this.eventTime = eventTime;
        if (isDone) {
            this.markAsDone();
        }
    }

    public String getEventTime() {
        return String.format("(at: %s)", eventTime);
    }

    @Override
    public String toString() {
        String result = taskType + this.getCheckBox()
                + " " + this.taskName + " "
                + getEventTime();
        return result;
    }
}
