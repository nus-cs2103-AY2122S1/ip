package duke.tasks;

import java.time.LocalDateTime;

public class Event extends Task {

    private Event(String description, LocalDateTime time) {
        super(description, "E", time);
    }

    public static Event create(String description, LocalDateTime time) {
        return new Event(description, time);
    }

    @Override
    public String toString() {
        return "[" + this.taskType + "]" + "[" + getStatusIcon() + "]" + " " + this.description
                + String.format(" (at: %s)", this.time.format(formatter));
    }
}
