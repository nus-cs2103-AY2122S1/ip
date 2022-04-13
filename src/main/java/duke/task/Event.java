package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <h2>Event</h2>
 * A task which has an additional <code>dateTime</code> field which informs the user when this event occurs.
 * @see Deadline
 * @see ToDo
 */

public class Event extends Task implements DateTimeTask {

    private final LocalDateTime dateTime;

    /**
     * Creates a new Event task. This is the default constructor since newly added tasks are by default not
     * completed
     * @param eventName the name of the task to be created
     * @param dateTime the date and time the event occurs
     */
    public Event(String eventName, LocalDateTime dateTime) {
        super(eventName);
        this.dateTime = dateTime;
    }

    private Event(Event oldEvent) {
        super(oldEvent);
        this.dateTime = oldEvent.dateTime;
    }

    /**
     * Factory method that creates Event objects that can either be completed or not completed
     * @param name the name of the task
     * @param isCompleted whether the event task to be created is completed or not
     * @param dateTime the date and time the deadline task needs to be completed by
     * @return a new event task object
     */
    public static Event createTask(String name, boolean isCompleted, LocalDateTime dateTime) {
        Event e = new Event(name, dateTime);
        if (isCompleted) {
            return new Event(e);
        } else {
            return e;
        }
    }

    public LocalDate getDate() {
        return this.dateTime.toLocalDate();
    }

    /**
     * {@inheritDoc}
     * @return a new <code>Event</code> which is exactly the same except with completion status set to
     * <code>true</code>.
     */
    @Override
    public Event markAsCompleted() {
        return new Event(this);
    }

    @Override
    public String toString() {
        return "E: " + super.toString() + " on: " + this.dateTime.format(DateTimeFormatter.ofPattern(
                Task.DATE_TIME_FORMAT));
    }
}
