package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Entry{

    private LocalDate event;

    Event() {
        super();
    }

    Event(String task, String event) throws DukeException {
        super(task);
        try {
            this.event = LocalDate.parse(event);
        } catch (DateTimeParseException e) {
            this.event = LocalDate.now();
            throw new DukeException("Invalid timing format! Enter dates in yyyy-mm-dd format");
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[E]" + super.toString() + "(at: " + this.event.format(formatter) + ")";
    }

    @Override
    public String saveString() {
        return "E" + super.saveString() + "," + this.event;
    }

    @Override
    public boolean isEmpty() { return super.isEmpty() && event.isAfter(LocalDate.now()); }
}
