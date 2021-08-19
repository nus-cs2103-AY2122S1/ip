public class Event extends Task {

    private String time;
    public taskType type;
    public Event(String taskName, String time) {
        super(taskName);
        this.time = time;
        type = taskType.EVENT;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at " + this.time + ")";
    }
}
