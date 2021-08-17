public class Event extends Task{

    public Event(String description, String time) {
        super(description, "E", time);
    }

    @Override
    public String toString() {
        return "[" + this.taskType + "]" + "[" + getStatusIcon() + "]" + " " + this.description
                + String.format("(at: %s)", this.time);
    }
}
