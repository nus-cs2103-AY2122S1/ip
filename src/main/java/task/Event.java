package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;

public class Event extends Task {

    private LocalDate at;

    /**
     * Constructs Event Task instance.
     *
     * @param name Name of task.
     * @param at Time of task.
     * @throws DukeException When name is empty.
     */
    public Event(String name, String at) throws DukeException {
        super(name);
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            throw new DukeException("incorrect date format");
        }
    }

    @Override
    public String saveTask() {
        return "event|" + this.getName() + "/at" + at + (this.isDone() ? "|1|" : "|0|") + this.saveTags();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
