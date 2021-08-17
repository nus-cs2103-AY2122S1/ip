public class Event extends Task {
    private String taskType = "[E]";
    private String taskName;
    private String eventTime;

    public Event(String taskName, String eventTime) {
        super(taskName);
        this.taskName = taskName;
        this.eventTime = eventTime;
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
