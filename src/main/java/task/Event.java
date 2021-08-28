package task;

import task.TimeTask;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends TimeTask {
    public Event(String description) {
        super(description);
    }
    public Event(String description, boolean isDone) {
        super(description, isDone);
    }

    public Event(String description, boolean isDone, LocalDate time) {
        super(description, isDone, time);
    }

    public Event(String description, String at) {
        super(description, at);
    }

    public Event(String description, boolean isDone, String at) {
        super(description, isDone, at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.getTime().format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
