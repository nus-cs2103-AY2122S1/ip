package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that is going to happen at a scheduled time.
 */
public class Event extends Task {

    private String time;
    private LocalDate eventTime;

    Event(String description, String time) {
        super(description);
        this.time = time;
        this.eventTime = setEventTime(time);
    }

    Event(String description, boolean isDone, String time) {
        super(description, isDone);
        this.time = time;
        this.eventTime = setEventTime(time);
    }

    /**
     * Returns the scheduled time of the Event object.
     *
     * @return The scheduled time of the event. If the original scheduled time
     * is given in a YYYY-MM-DD format, the scheduled time will be returned in a MMM d yyyy format.
     */
    public String getTime() {
        if (this.eventTime == null) {
            return this.time;
        } else {
            return this.eventTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
    }

    private LocalDate setEventTime(String time) {
        try {
            LocalDate date = LocalDate.parse(time);
            return date;
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Returns the Event object in a string format suitable for storing in file.
     *
     * @return String of the Event object in the correct format for storing in file.
     */
    @Override
    public String saveTaskToFile() {
        return this.getTypeOfTask() + "||" + this.getStatusIcon() + "||" + this.getDescription() + "||" + this.getTime();
    }

    /**
     * Returns the type of task. Always return "E" which stands of the "E" in Event.
     *
     * @return "E".
     */
    @Override
    public String getTypeOfTask() {
        return "E";
    }

    /**
     * Returns the Event object in a string format.
     *
     * @return String in the format of "[E][marked as done?]_eventDescription_(at:_scheduledTime)."
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s (at: %s)", this.getTypeOfTask(),
                this.getStatusIcon(), this.getDescription(), this.getTime());
    }
}
