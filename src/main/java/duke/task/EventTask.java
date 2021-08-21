package duke.task;

import java.time.LocalDate;

/**
 * A task specifying an event with a date and time.
 */
public class EventTask extends Task {

    public EventTask(String task) {
        super(task);
        String[] arr = task.split(" /at ", 2);
        this.date = arr.length >= 2 ? LocalDate.parse(arr[1]) : null;
        this.name = arr[0];
    }

    public EventTask(String name, boolean completed) {
        super(name, completed);
        this.date = null;
    }

    public EventTask(String name, boolean completed, LocalDate date) {
        super(name, completed);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getDateString() + ")";
    }

}
