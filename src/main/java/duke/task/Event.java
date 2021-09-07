package duke.task;

public class Event extends Task {
    private String taskType = "[E]";

    public Event(String taskName, String eventTime) {
        super(taskName, eventTime);
    }

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
