package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate at;

    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at);
    }

    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = LocalDate.parse(at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() 
                + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) 
                + ")";
    }

    @Override
    public String saveText() {
        return "E" + " / " + super.saveText() + " / " + at;
    }
}
