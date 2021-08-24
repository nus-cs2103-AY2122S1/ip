package duke.task;

import java.time.LocalDateTime;

/**
 * Event class.
 * Used to represent a event task.
 *
 * @author KelvinSoo
 * @version Level-8
 *
 */
public class Event extends Task {
    private String eventTime;

    /**
     * A constructor to create a new event task.
     */
    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
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
     * A constructor to create a new event task with supported time format.
     */
    public Event(String description, LocalDateTime localDateTime) {
        super(description);
        this.eventTime = String.format("%s of %s %s, %s%s",
                localDateTime.getDayOfMonth(),
                localDateTime.getMonth().toString(),
                localDateTime.getYear(),
                localDateTime.getHour() < 12 ? localDateTime.getHour() : localDateTime.getHour() - 12,
                localDateTime.getHour() < 12 ? "am" : "pm");
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
        return String.format("%s (at: %s)", super.getDescription(), this.eventTime);
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
