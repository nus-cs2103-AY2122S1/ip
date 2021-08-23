package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.IrisException;

public class Event extends Task{
    protected LocalDate at;
    public Event(String name, String at) throws IrisException {
        super(name);
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException exception) {
            throw new IrisException("Invalid date provided.");
        }
    }

    @Override
    public String toString() {
        return String.format(
                "[E]%s (at: %s)",
                super.toString(),
                this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
        );
    }

    @Override
    public String toCommand(int index) {
        return String.format(
                "event %s /at %s\n%s",
                this.name,
                this.at,
                super.toCommand(index)
        );
    }
}
