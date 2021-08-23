public class EventTask extends Task {
    protected String at;

    public EventTask(String description, String at) {
        super(description);
        this.at = at;
    }

    public EventTask(String isCompleted, String description, String at) {
        super(isCompleted, description);
        this.at = at;
    }

    public String getDetails() {
        return this.at;
    }

    public String getType() {
        return "EVENT";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}