public class Event extends Task {
    protected String timeStart;

    public Event(String description, String timeStart) {
        super(description);
        this.timeStart = timeStart;
    }

    public Event(String description, boolean isDone, String timeStart) {
        super(description, isDone);
        this.timeStart = timeStart;
    }

    @Override
    public String taskToLine() {
        return "D" + super.taskToLine() + " | " + this.timeStart;
    }

    @Override
    public String toString() {
        String description = super.toString();
        return "[E]" + description + " (at: " + this.timeStart + ")";
    }
}
