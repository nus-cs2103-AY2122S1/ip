public class Event extends Task {
    private String at;

    public Event(String taskName, boolean doneStatus, String at) {
        super(taskName, doneStatus);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.at + ")";
    }
}
