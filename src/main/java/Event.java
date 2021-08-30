import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected String at;
    protected String date = "";

    public Event(String description, String at) throws DukeException {
        super(description);

        try {
            LocalDate localDate = LocalDate.parse(at);
            this.date = localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            throw new DukeException("Event date should be in a yyyy-mm-dd format.");
        }

        if (description.isEmpty() || description == "" || description == " ") {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        } else {
            this.description = description.substring(1);
        }

        if (at.isEmpty() || at == "" || at == " ") {
            throw new DukeException("☹ OOPS!!! The time of the event must be indicated.");
        } else {
            this.at = this.date;
        }
    }

    @Override
    public String toString() {
        return "\t[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String getType() {
        return "event";
    }

    @Override
    public String addOns() {
        return this.at;
    }
}
