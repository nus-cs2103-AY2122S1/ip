package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task. A deadline task has to be completed by a certain date and time.
 */
public class Deadline extends Task{

    private String by;
    private LocalDate date;
    private int time;

    public Deadline(String details, String by, String date, int time) {
        super(details);
        this.by = by;
        this.date = date == null
                ? null
                : LocalDate.parse(date);
        this.time = time;
    }

    public String getBy() {
        return this.by;
    }

    /**
     * Returns date of task.
     *
     * @return Task date.
     */
    @Override
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Returns time of task.
     *
     * @return Task time.
     */
    @Override
    public int getTime() {
        return this.time;
    }

    /**
     * Converts a 24hr time to a 12hr time.
     *
     * @return 12hr time as a String.
     */
    private String twelveHrTime() {
        String timeStr = "";
        if (this.time == 1200) {
            timeStr = "12:00PM";
        } else if (this.time == 0) {
            timeStr = "12:00AM";
        } else {
            String min = this.time % 100 >= 10
                    ? "" + (this.time % 100)
                    : "0" + (this.time % 100);
            if (time > 1159) {
                timeStr = timeStr + (((this.time / 100) - 12) + ":" + min + "PM");
            } else {
                timeStr = timeStr + ((this.time / 100) + ":" + min + "AM");
            }
        }
        return timeStr;
    }

    /**
     * Returns a String representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        if (date == null) {
            return "[D]" + super.toString() + " (by: " + by + ")";
        } else {
            return "[D]" + super.toString() + " (by: "
                    + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + ' ' + twelveHrTime() + ")";
        }
    }
}
