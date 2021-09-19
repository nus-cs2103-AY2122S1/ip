package duke;

import java.time.LocalDateTime;

/**Child class that extends parent 'Task' class and handles
 * operations for the Event task.
 * */
public class Event extends Task{
    private LocalDateTime at;

    /**
     * Constructor for the Event class.
     *
     * @param description The description of the task
     * @param at The date on which the task must be executed
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Converts the date of the object from type LocalDateTime
     * to String for easy manipulation
     *
     * @param dateTime The time of the task stored in list as LocalDateTime
     * @return The manipulated string of the object's date and time
     */
    public String getEventDateTimeString(LocalDateTime dateTime) {
        String dateTimeString = dateTime.toString();
        String[] splitDateAndTime = dateTimeString.split("T");
        String DateTime = splitDateAndTime[0] + " " + splitDateAndTime[1];
        return DateTime;
    }

    /**
     * toString() method for event object
     *
     * @return The task printed in the correct format
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "at: " + getEventDateTimeString(this.at);
    }
}
