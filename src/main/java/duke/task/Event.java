package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime at;
    private DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("dd MMM uuuu HHmm");
    private DateTimeFormatter saveFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu HHmm");

    public Event(String description, LocalDateTime at) {
        super(description, false);
        this.at = at;
    }

    public Event(String description, String isDone, LocalDateTime at) {
        super(description, isDone == "1");
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(displayFormatter) + ")" ;
    }

    @Override
    public String toSaveString() {
        return "E|" + super.toSaveString() + "|" + at.format(saveFormatter);
    }
}
