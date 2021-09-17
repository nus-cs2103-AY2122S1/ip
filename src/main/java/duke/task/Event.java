package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDate at;
    private boolean isTimeIncluded;

    /**
     * Constructor for Event.
     *
     * @param input event title.
     * @param at date/time of event.
     * @throws DateTimeParseException if date format is incorrect.
     */

    public Event(String input, String at) throws DateTimeParseException {
        super(input);
        int space = at.indexOf(" ");
        String format;
        if (space > 0) {
            format = "d/MM/yyyy HHmm";
            isTimeIncluded = true;
        } else {
            format = "d/MM/yyyy";
            isTimeIncluded = false;
        }
        this.at = LocalDate.parse(at.trim(), DateTimeFormatter.ofPattern(format));
        if (this.at.isBefore(LocalDate.now())) {
            super.completeItem();
        }
    }

    @Override
    public boolean compareDate(LocalDate compare) {
        return this.at.equals(compare);
    }

    @Override
    public String formatTask() {
        return "E" + " | " + super.formatTask() + " | " + this.at.format(DateTimeFormatter.ofPattern("d/MM/yyyy"));
    }

    @Override
    public String toString() {
        String format = isTimeIncluded ? "MMM dd yyyy HH:mm" : "MMM dd yyyy";
        return "[E] " + super.toString() + "(at: " + this.at.format(DateTimeFormatter.ofPattern(format)) + ")";
    }
}
