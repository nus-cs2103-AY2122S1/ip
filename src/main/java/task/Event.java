package task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Event extends Task {
    /** The date of the task. **/
    private final LocalDate date;

    /** The time of the task. **/
    private final LocalTime time;

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
     * A public method to get the date of the task.
     *
     * @return A LocalDate, the date of the task.
     */
    @Override
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * A public method to get the time of the task.
     *
     * @return A LocalTime, the time of the task.
     */
    public LocalTime getTime() {
        if (this.time != null) {
            return this.time;
        }
        return LocalTime.of(23, 59);
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
                    this.getTypeIcon(), this.getStatusIcon(), this.getDescription(),
                    this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.US)));
        } else {
            return String.format("[%s][%s] %s (at: %s %s)",
                    this.getTypeIcon(), this.getStatusIcon(), this.getDescription(),
                    this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.US)), this.time);
        }
    }

    /**
     * A method to check whether this task happen on a given date.
     *
     * @param date The given date to check.
     * @return A boolean value indicating whether the task happen on that day.
     */
    @Override
    public boolean isOnDate(LocalDate date) {
        if (this.date != null && this.date.equals(date)) {
            return true;
        }
        return false;
    }
}
