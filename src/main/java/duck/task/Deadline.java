package duck.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a user-given deadline. The deadline can be a date, or a date and time.
 */
public class Deadline extends Task {
    private final LocalDate deadlineDate;
    private LocalTime deadlineTime;
    private final boolean isTimeGiven;

    /**
     * Constructor for a deadline on a given day.
     *
     * @param taskName The task name/description.
     * @param deadlineDate The deadline.
     */
    public Deadline(String taskName, LocalDate deadlineDate) {
        super(taskName);
        this.deadlineDate = deadlineDate;
        isTimeGiven = false;
    }

    /**
     * Constructor for a deadline on a given day at a given time.
     *
     * @param taskName The task name/description.
     * @param deadlineDate The date of the deadline.
     * @param deadlineTime The time of the deadline.
     */
    public Deadline(String taskName, LocalDate deadlineDate, LocalTime deadlineTime) {
        super(taskName);
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
        isTimeGiven = true;
    }

    /**
     * Returns this Deadline's list entry string.
     *
     * @return A string representation of this Deadline's list entry.
     */
    @Override
    public String listEntry() {
        if (isTimeGiven) {
            return "[D]" + super.listEntry()
                    + " (by " + deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + " at " + deadlineTime.format(DateTimeFormatter.ofPattern("h:mm a")) + ")";
        } else {
            return "[D]" + super.listEntry()
                    + " (by " + deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
    }

    /**
     * Returns this Deadline's database entry string.
     *
     * @return A string representation of this Deadline's database entry.
     */
    @Override
    public String databaseEntry() {
        if (isTimeGiven) {
            return "D" + super.databaseEntry() + " | " + deadlineDate + " " + deadlineTime;
        } else {
            return "D" + super.databaseEntry() + " | " + deadlineDate;
        }
    }

    /**
     * Checks if this Deadline object's deadline is on the given date.
     *
     * @param givenDate The date against which to check this Deadline object's deadline.
     * @return true if the deadline is on the given date.
     */
    @Override
    public boolean isTodayTask(LocalDate givenDate) {
        return givenDate.isEqual(deadlineDate);
    }

    @Override
    public int deadlineCompare(Deadline otherDeadline) {
        return this.deadlineTime.compareTo(otherDeadline.deadlineTime);
    }

    @Override
    public int eventCompare(Event otherEvent) {
        return otherEvent.timeCompare(deadlineTime);
    }

    public int timeCompare(LocalTime time) {
        return time.compareTo(deadlineTime);
    }

    public boolean isWholeDay() {
        return !isTimeGiven;
    }
}
