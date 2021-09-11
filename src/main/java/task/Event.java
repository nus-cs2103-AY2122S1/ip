package task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import exception.InvalidDateFormat;

public class Event extends Task {

    private LocalDate date;

    DateTimeFormatter dayOutputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
    DateTimeFormatter dayInputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Event(String description, String deadline, boolean completed) throws InvalidDateFormat {
        super(description, completed);
        try {
            this.date = LocalDate.parse(deadline,dayInputFormatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormat();
        }
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(),
                date.format(dayOutputFormatter));
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String getDeadline() {
        return this.date.toString();
    }
}
