package task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    
    private final boolean hasTime;
    private final boolean hasEndDate;

    public Event(String taskName, LocalDate startDate, LocalTime startTime, LocalTime endTime) {
        super(taskName);
        this.startDate = startDate;
        this.startTime = startTime;
        this.endTime = endTime;
        hasTime = true;
        hasEndDate = false;
    }

    public Event(String taskName, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        super(taskName);
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        hasTime = true;
        hasEndDate = true;
    }

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
                return "E" + super.databaseEntry() + " " + startDate + " " + startTime + " " + endDate + " " + endTime;
            } else {
                return "E" + super.databaseEntry() + " " + startDate + " " + startTime + " " + endTime;
            }
        } else {
            return "E" + super.databaseEntry() + " " + startDate + " " + endDate;
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
}
