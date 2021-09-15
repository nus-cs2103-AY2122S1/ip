package whobot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import whobot.main.WhoBotException;

/***
 * Class to Handle Event type Tasks
 */
public class Event extends Task {

    /** Timing for the Event */
    private final LocalDateTime timing;

    /***
     * Constructor for the Event Class
     *
     * @param task the string description of the Event Task
     * @throws WhoBotException If the format of timing or command is not correct
     */
    public Event(String task) throws WhoBotException {
        super(task.split(" /at ")[0]);
        try {
            this.timing = LocalDateTime.parse(task.split(" /at ")[1], DateTimeFormatter.ofPattern("d/M/yyyy HH:mm"));
        } catch (DateTimeParseException ex) {
            throw new WhoBotException("Ensure that date time is of the format d/M/yyyy HH:mm");
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new WhoBotException("Ensure that the command is of the form \"event #description /at #timing\"."
                    + " The timing must be given.");
        }
    }

    /***
     * Returns the output formatted date time of the event
     *
     * @return String containing the output format date and time
     */
    public String getDateTimeFormatted() {
        return this.timing.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
    }

    /***
     * Returns the String description of the task along with the formatted timing
     *
     * @return String representation of the Event Task
     */
    @Override
    public String getDescription() {
        return "[E] " + super.getDescription() + " (at: " + this.getDateTimeFormatted() + ")";
    }

    /***
     * Returns the Type of Task
     *
     * @return E since Event Type
     */
    @Override
    public String getType() {
        return "E";
    }

    /***
     * Returns timing for the Event
     *
     * @return timing
     */
    public LocalDateTime getTiming() {
        return timing;
    }

    /***
     * Returns string representation of the task
     *
     * @return string to display for the task
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (at: " + this.getDateTimeFormatted() + ")";
    }

    /***
     * Compares this task to another, to help with sorting
     *
     * @param o Task to compare to
     * @return result after comparing the timings of the task
     */
    @Override
    public int compareTo(Task o) {
        int val = super.compareTo(o);
        if (val == 0) {
            if (o instanceof Event) {
                return this.timing.compareTo(((Event) o).timing);
            } else if (o instanceof Deadline) {
                return this.timing.compareTo(((Deadline) o).getDeadline());
            } else {
                return this.getDescription().compareTo(o.getDescription());
            }
        } else {
            return val;
        }
    }

    /***
     * Equates this task to another
     *
     * @param o Task to equate to
     * @return true if both have the same description, same timing and are done
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Event event = (Event) o;
        return timing.equals(event.timing) && this.getDescription().equals(event.getDescription());
    }
}
