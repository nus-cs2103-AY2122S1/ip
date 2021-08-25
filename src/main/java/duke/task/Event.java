package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate timeStart;

    public Event(String description, LocalDate timeStart) {
        super(description);
        this.timeStart = timeStart;
    }

    public Event(String description, boolean isDone, LocalDate timeStart) {
        super(description, isDone);
        this.timeStart = timeStart;
    }

    @Override
    public String taskToLine() {
        return "D" + super.taskToLine() + " | " + this.timeStart;
    }

    @Override
    public String toString() {
        String description = super.toString();
        return "[E]" + description + " (at: "
                + this.timeStart.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
