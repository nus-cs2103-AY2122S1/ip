package duke.task;

import duke.task.Task;

public class Event extends Task {
    private String taskType = "[E]";
    private String taskName;
    private String eventTime;
    private boolean isDone;

    public Event(String taskName, String eventTime) {
        super(taskName);
        this.taskName = taskName;
        this.eventTime = eventTime;
    }

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
