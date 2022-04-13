package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exception.InvalidDateFormat;

public class Event extends Task {

    private LocalDate date;

    private DateTimeFormatter dayOutputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
    private DateTimeFormatter dayInputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Constructs a new Event task.
     *
     * @param description the description of the task.
     * @param deadline the deadline of the task.
     * @param notes additional notes regarding the task
     * @param completed true if the task is completed.
     * @throws InvalidDateFormat if the date is written in the wrong format.
     */
    public Event(String description, String deadline, String notes, boolean completed) throws InvalidDateFormat {
        super(description, notes, completed);
        try {
            this.date = LocalDate.parse(deadline, dayInputFormatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormat();
        }
    }

    private String showNotesIfAvailable() {
        if (this.notes.isEmpty()) {
            return "";
        } else {
            return "--" + this.notes + "\n";
        }
    }

    /**
     * Returns the string representation of the Event Object.
     *
     * @return the full string representation of Event Object.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)\n%s", super.toString(),
                date.format(dayOutputFormatter), showNotesIfAvailable());
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String getDeadline() {
        return this.date.format(dayInputFormatter);
    }
}
