package duke.task;

import duke.utility.Utility;

import java.time.LocalDateTime;

/**
 * Event class.
 * Used to represent a event task.
 * @author KelvinSoo
 * @version Level-8
 */
public class Event extends Task {
    private String eventTime;
    private final int HALF_DAY_HOURS = 12;

    /**
     * A constructor to create a new event task.
     */
    public Event(String description, String eventTime) {
        super(description);
        LocalDateTime ldt = Utility.stringToDate(eventTime);
        if (ldt == null) {
            this.eventTime = eventTime;
        } else {
            boolean isDayTime = ldt.getHour() < HALF_DAY_HOURS;
            this.eventTime = String.format("%s of %s %s, %s%s",
                    ldt.getDayOfMonth(),
                    ldt.getMonth().toString(),
                    ldt.getYear(),
                    isDayTime ? ldt.getHour() : ldt.getHour() - HALF_DAY_HOURS,
                    isDayTime ? "am" : "pm");
        }
    }

    /**
     * A constructor to create a new event task.
     */
    public Event(String description, String eventTime, Boolean isDone) {
        super(description);
        if (isDone) {
            super.markAsDone();
        }
        this.eventTime = eventTime;
    }

    /**
     * A method to get the state of the task.
     * @return The status icon
     */
    @Override
    public String getStatusIcon() {
        return "[E]" + super.getStatusIcon();
    }

    /**
     * A method to get data about the task in a savable format.
     * @return The savable data
     */
    @Override
    public String getMetaData() {
        return String.format("E|%s|%s", super.getMetaData(), eventTime);
    }

    /**
     * A method to get the description of the task.
     * @return The description of the task including date
     */
    @Override
    public String getDescription() {
        return String.format("%s (at: %s)", super.getDescription(), eventTime);
    }

    /**
     * A method to get all task details.
     * @return All the task details
     */
    @Override
    public String toString() {
        return String.format("%s %s", getStatusIcon(), getDescription());
    }
}
