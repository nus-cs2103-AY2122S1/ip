import java.util.Optional;

public class Event extends Task{

    private Event(String description, String time) {
        super(description, "E", time);
    }

    public static Event create(String description, String time) {
        return new Event(description, time);
    }

    @Override
    public String toString() {
        return "[" + this.taskType + "]" + "[" + getStatusIcon() + "]" + " " + this.description
                + String.format("(at:%s)", this.time);
    }
}
