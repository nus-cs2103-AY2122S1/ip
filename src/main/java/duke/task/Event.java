package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDate time;

    /**
     * Creates a new Task object with the time of the task.
     *
     * @param description The description of the task.
     * @param time The time of the task.
     */
    public Event(String description, String time) {
        super(description);
        this.time = LocalDate.parse(time);
    }

    /** Returns the time of the event as a LocalDate object */
    public LocalDate getTime() {
        return this.time;
    }

    /** Returns the time of the event as a String */
    private String timeToString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return this.time.format(formatter);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.timeToString());
    }
}
