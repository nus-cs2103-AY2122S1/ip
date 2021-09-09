package whobot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import whobot.main.WhoBotException;

/***
 * Class to Handle Deadline type Tasks
 */
public class Deadline extends Task {

    /** Deadline of the Task */
    private LocalDateTime deadline;

    /** Whether the Deadline is time specific */
    private boolean hasTime;

    /***
     * Constructor for the Deadline Class
     *
     * @param task the string description of the Deadline Task
     * @throws WhoBotException If the format of timing or command is not correct
     */
    public Deadline(String task) throws WhoBotException {
        super(task.split(" /by ")[0]);
        try {
            this.deadline = processDateTime(task.split(" /by ")[1]);
        } catch (DateTimeParseException ex) {
            throw new WhoBotException("Ensure that date time is of the format d/M/yyyy HH:mm");
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new WhoBotException("Ensure that the command is of the form \"deadline #description /by #deadline\"."
                    + " The deadline must be given.");
        }
    }

    /***
     * Returns the processed final date and time of the event from string
     *
     * @param dateTime The String to get DateTime from.=
     * @return LocalDateTime showing the deadline for the Event
     */
    private LocalDateTime processDateTime(String dateTime) {
        if (dateTime.contains(" ")) {
            this.hasTime = true;
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("d/M/yyyy HH:mm"));
        } else {
            this.hasTime = false;
            return LocalDateTime.parse(dateTime + " 08:00", DateTimeFormatter.ofPattern("d/M/yyyy HH:mm"));
        }
    }

    /***
     * Returns whether the deadline has a time
     *
     * @return hasTime
     */
    public boolean hasTime() {
        return hasTime;
    }


    /***
     * Returns the Deadline as a formatted string
     *
     * @return Date and Time (if applicable) as String
     */
    public String getDateTimeFormatted() {
        return hasTime
                ? this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"))
                : this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /***
     * Returns the String description of the task along with the formatted deadline
     *
     * @return String representation of the Deadline Task
     */
    @Override
    public String getDescription() {
        return "[D] " + super.getDescription() + " (by: " + this.getDateTimeFormatted() + ")";
    }

    /***
     * Returns the Type of Task
     *
     * @return D since Deadline Type
     */
    @Override
    public String getType() {
        return "D";
    }

    /***
     * Returns deadline
     *
     * @return deadline
     */
    public LocalDateTime getDeadline() {
        return deadline;
    }

    /***
     * Returns string representation of the task
     *
     * @return string to display for the task
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.getDateTimeFormatted() + ")";
    }

    /***
     * Compares this task to another, to help with sorting
     *
     * @param o Task to compare to
     * @return result after comparing the deadline of the task
     */
    @Override
    public int compareTo(Task o) {
        int val = super.compareTo(o);
        if (val == 0) {
            if (o instanceof Deadline) {
                return this.deadline.compareTo(((Deadline) o).deadline);
            } else if (o instanceof Event) {
                return this.deadline.compareTo(((Event) o).getTiming());
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
     * @return true if both have the same description, same deadline and are done
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Deadline deadline1 = (Deadline) o;
        return deadline.equals(deadline1.deadline) && this.hasTime == deadline1.hasTime
                && this.getDescription().equals(deadline1.getDescription());
    }
}
