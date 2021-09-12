package duck.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that takes place over a period of time. The event can take place between dates, on one date
 * between two times, or date-time to date-time.
 */
public class Event extends Task {
    private final LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;

    private final boolean hasTime;
    private final boolean hasEndDate;

    /**
     * Constructor for an event taking place on one day, with a start and end time.
     *
     * @param taskName The task's name/description.
     * @param startDate The date of the event.
     * @param startTime The time at which the event starts.
     * @param endTime The time at which the event ends.
     */
    public Event(String taskName, LocalDate startDate, LocalTime startTime, LocalTime endTime) {
        super(taskName);
        this.startDate = startDate;
        this.startTime = startTime;
        this.endTime = endTime;
        hasTime = true;
        hasEndDate = false;
    }

    /**
     * Constructor for an event taking place over multiple days, with start and end times.
     *
     * @param taskName The task's name/description.
     * @param startDate The start date of the event.
     * @param startTime The time at which the event starts on the start date.
     * @param endDate The end date of the event.
     * @param endTime The time at which the event ends on the end date.
     */
    public Event(String taskName, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        super(taskName);
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        hasTime = true;
        hasEndDate = true;
    }

    /**
     * Constructor for an event taking place over multiple days.
     *
     * @param taskName The task's name/description.
     * @param startDate The start date of the event.
     * @param endDate The end date of the event.
     */
    public Event(String taskName, LocalDate startDate, LocalDate endDate) {
        super(taskName);
        this.startDate = startDate;
        this.endDate = endDate;
        hasTime = false;
        hasEndDate = true;
    }

    /**
     * Returns this Event's list entry string.
     *
     * @return A string representation of this Event's list entry.
     */
    @Override
    public String listEntry() {
        if (hasTime) {
            if (hasEndDate) {
                return "[E]" + super.listEntry()
                        + " (from " + startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                        + " at " + startTime.format(DateTimeFormatter.ofPattern("h:mm a"))
                        + " to " + endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                        + " at " + endTime.format(DateTimeFormatter.ofPattern("h:mm a")) + ")";
            } else {
                return "[E]" + super.listEntry()
                        + " (on " + startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                        + " from " + startTime.format(DateTimeFormatter.ofPattern("h:mm a"))
                        + " to " + endTime.format(DateTimeFormatter.ofPattern("h:mm a")) + ")";
            }
        } else {
            return "[E]" + super.listEntry()
                    + " (from " + startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + " to " + endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
    }

    /**
     * Returns this Event's database entry string.
     *
     * @return A string representation of this Event's database entry.
     */
    @Override
    public String databaseEntry() {
        if (hasTime) {
            if (hasEndDate) {
                return "E" + super.databaseEntry() + " | " + startDate + " " + startTime + " " + endDate + " " + endTime;
            } else {
                return "E" + super.databaseEntry() + " | " + startDate + " " + startTime + " " + endTime;
            }
        } else {
            return "E" + super.databaseEntry() + " | " + startDate + " " + endDate;
        }
    }

    /**
     * Checks if this Event object's period contains the given date.
     *
     * @param l The date against which to check this Event object's period.
     * @return true if the event is ongoing on the given date.
     */
    @Override
    public boolean isTodayTask(LocalDate l) {
        return l.isEqual(startDate) || l.isEqual(endDate) || (l.isAfter(startDate) && l.isBefore(endDate));
    }

    @Override
    public int deadlineCompare(Deadline otherDeadline) {
        return otherDeadline.timeCompare(startTime);
    }

    @Override
    public int eventCompare(Event otherEvent) {
        return this.startTime.compareTo(otherEvent.startTime);
    }

    public int timeCompare(LocalTime time) {
        return time.compareTo(startTime);
    }

    public boolean isWholeDay() {
        return !hasTime;
    }
}
