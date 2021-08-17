public class Event extends Task {
    String time;

    public Event(String taskName, String time) {
        super(taskName);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + this.time + ")";
    }
}
