package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that starts and ends at a specific time.
 * E.g. team project meeting at Oct-10-2019 1300hrs
 */
public class Event extends Task {
    protected LocalDate date;
    protected LocalTime time;

    /**
     * Class constructor
     * @param description Description of the event.
     * @param at Time and Date of event.
     */
    public Event(String description, String at) {
        super(description);
        this.date = LocalDate.parse(at.split(" ", 2)[0]);
        this.time = LocalTime.parse(at.split(" ", 2)[1]);
    }

    /**
     * Returns the date of the event formatted as [MMM-dd-yyyy]
     *
     * @return The formatted date.
     */
    public String getDate() {
        DateTimeFormatter dateFormatObj = DateTimeFormatter.ofPattern("MMM-dd-yyyy");
        String formattedDate = date.format(dateFormatObj);
        return formattedDate;
    }

    /**
     * Returns the time of the event formatted as [HH:mm].
     *
     * @return The formatted time.
     */
    public String getTime() {
        DateTimeFormatter timeFormatObj = DateTimeFormatter.ofPattern("HH:mm");
        String formattedTime = time.format(timeFormatObj);
        return formattedTime;
    }

    /**
     * Returns formatted string to write to the file.
     *
     * @return String to write to duke.txt
     */
    @Override
    public String toWriteFormat() {
        String done = this.isDone ? "1" : "0";
        return String.format("E | %s | %s | %s %s", done, this.getDescription(), this.date, this.time);
    }

    /**
     * Returns the string representation of the Event.
     *
     * @return String representation of Event.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s %s hrs)", super.toString(), this.getDate(), this.getTime());
    }

    /**
     * Checks if the current task object is the same as a given task object.
     *
     * @param obj The given task object.
     * @return True if equals, False if not equals.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Event other = (Event) obj;
        if (!this.description.equals(other.getDescription())
            || !this.getDate().equals(other.getDate())
            || !this.getTime().equals(other.getTime())) {
            return false;
        }
        return true;
    }

    /** Returns a hash of the current object.
     *
     * @return The hash.
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.description != null ? this.description.hashCode() : 0);
        hash = 53 * hash + (this.date != null ? this.date.hashCode() : 0);
        hash = 53 * hash + (this.time != null ? this.time.hashCode() : 0);
        return hash;
    }
}
