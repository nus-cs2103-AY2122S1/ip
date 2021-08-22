package task;

import task.Task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Event extends Task {
    public LocalDate date;

    public LocalTime time;

    /**
     * A public constructor to initialize the task.Deadline.
     *
     * @param description The description of the task.
     * @param date The latest completion date of the task.
     * @param time The latest completion time of the task.
     */
    public Event(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    /**
     * A public constructor to initialize the task.Deadline.
     *
     * @param description The description of the task.
     * @param date The latest completion date of the task.
     */
    public Event(String description, LocalDate date) {
        super(description);
        this.date = date;
        this.time = null;
    }

    /**
     * A public method to generate the type icon of the task.
     *
     * @return A String. The type icon of the task.
     */
    public String getTypeIcon() {
        return "E";
    }

    /**
     * A override toString() method.
     *
     * @return A String. The String representation of the task.
     */
    @Override
    public String toString() {
        if (this.time == null) {
            return String.format("[%s][%s] %s (at: %s)",
                    this.getTypeIcon(), this.getStatusIcon(), this.description,
                    this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.US)));
        } else {
            return String.format("[%s][%s] %s (at: %s %s)",
                    this.getTypeIcon(), this.getStatusIcon(), this.description,
                    this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.US)), this.time);
        }
    }
}
