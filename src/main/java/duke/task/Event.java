package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy hh:mma");
    private static final DateTimeFormatter saveFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    protected LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(formatter) + ")";
    }

    @Override
    public String formatSave() {
        return "E%," + isDone + "%," + description + "%," + at.format(saveFormatter);
    }
}
