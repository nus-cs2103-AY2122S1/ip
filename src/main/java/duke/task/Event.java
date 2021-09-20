package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A type of duke.Task that keeps track of the date and time of a task and the task's description.
 */
public class Event extends Task {
    protected LocalDate at;
    private final String DATE_FORMAT = "MMM d yyyy";

    /**
     * Constructor.
     *
     * @param description details about the Task.
     * @param at date/time when the Task takes place, in yyyy-mm-dd format.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns duke.Event's String form.
     *
     * @return String.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern(DATE_FORMAT)) + ")";
    }

    /**
     * Returns the task type of duke.Event.
     *
     * @return character E to represent duke.Event task type.
     */
    @Override
    public char getTaskType() {
        return 'E';
    }

    /**
     * Returns the description in the format that will be saved into Hard drive.
     *
     * @return String.
     */
    @Override
    public String toSavedFormat() {
        return super.description + "/~/" + this.at;
    }

    public boolean isSameDate(LocalDate date) {
        return at.equals(date);
    }
}
