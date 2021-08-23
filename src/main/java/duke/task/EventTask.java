package duke.task;

import java.time.LocalDate;

/**
 * A task specifying an event with a date and time.
 */
public class EventTask extends Task {

    public EventTask(String name, LocalDate date) {
        super(name, date);
    }

    public EventTask(String name, boolean completed) {
        super(name, completed);
    }

    public EventTask(String name, boolean completed, LocalDate date) {
        super(name, completed, date);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getDateString() + ")";
    }

}
