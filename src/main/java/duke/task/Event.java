package duke.task;

public class Event extends Task {

    private String timeFrame;

    public Event (String description, String timeFrame) {
        super(description);
        this.timeFrame = timeFrame;

    }

    @Override
    public String getData() {
        return String.format("E,%s,%s", this.timeFrame, super.getData());
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.timeFrame);
    }
}
