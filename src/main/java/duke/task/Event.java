package duke.task;

public class Event extends Task {
    private String at;
    private String tag = "E";

    public Event(String taskName, String at) {
        super(taskName);
        this.at=at;
    }

    @Override
    public String getAdditionalInfo() {
        return at;
    }

    @Override
    public String getTag() { return tag; }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}