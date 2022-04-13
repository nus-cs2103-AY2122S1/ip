package pika.task;

import java.time.format.DateTimeParseException;

import pika.exception.PikaException;

/**
 * Event Class to handle event tasks.
 */
public class Event extends Task {
    protected String eventTime;

    /**
     * Constructor for the event class.
     *
     * @param name      Name of the event Task.
     * @param eventTime Date or Date and Time for the event Task.
     * @throws PikaException If the date or time input is not valid.
     */
    public Event(String name, String eventTime) throws PikaException { //Event class to handle Event task
        super(name);
        try {
            this.eventTime = TaskTime.convertDateTimeFormat(eventTime);
        } catch (DateTimeParseException e) {
            throw new PikaException("Pika pi!! Your event date input format is not valid!");
        }
    }

    /**
     * Returns the string to be written in the txt file.
     *
     * @return txt format of the task for storage.
     */
    @Override
    public String write() {
        String tags = " |" + getTags();
        if (tags.equals(" |")) {
            return "E " + super.write() + " | " + this.eventTime;
        } else {
            return "E " + super.write() + " | " + this.eventTime + " |" + getTags();
        }
    }

    /**
     * Returns the list format of the task.
     *
     * @return list format of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.eventTime + ")";
    }
}
