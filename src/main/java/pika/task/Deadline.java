package pika.task;

import java.time.format.DateTimeParseException;

import pika.exception.PikaException;

/**
 * Deadline Class to handle Deadline Tasks.
 */
public class Deadline extends Task {
    protected String dueDate;

    /**
     * Constructor for Deadline Class.
     *
     * @param name    name of the task.
     * @param dueDate Due date of the task. Can be Date or Date/Time.
     * @throws PikaException If the date or time input is not valid.
     */
    public Deadline(String name, String dueDate) throws PikaException {
        super(name);
        try {
            this.dueDate = TaskTime.convertDateTimeFormat(dueDate);
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
            return "D " + super.write() + " | " + this.dueDate;
        } else {
            return "D " + super.write() + " | " + this.dueDate + " |" + getTags();
        }
    }

    /**
     * Returns the task for the list format.
     *
     * @return The task in string format.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dueDate + ")";
    }
}
