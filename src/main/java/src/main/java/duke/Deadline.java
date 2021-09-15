package src.main.java.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class to represent a task as a with a deadline.
 */


public class Deadline extends Task {

    private String task;
    private boolean isDone;
    private LocalDate time;

    Deadline(String T, boolean D, String time) throws DukeException {
        super(T, D);
        task = T;
        isDone = D;

        try {
            this.time = LocalDate.parse(time);
        } catch (DateTimeParseException error) {
            throw new InvalidInputException("Please Enter date in this format 'YYYY-MM-dd'");
        }

    }

    /**
     * overridden method to mark the task as done.
     */
    @Override
    void markAsDone() {
        this.isDone = true;
    }

    /**
     * method to update time of the deadline task.
     *
     * @param time time in String to be converted to LacalDate.
     */
    @Override
    void setTime(String time) throws DukeException {
        try {
            this.time = LocalDate.parse(time);
        } catch (DateTimeParseException error) {
            throw new InvalidInputException("Please Enter date in this format 'YYYY-MM-dd'");
        }
    }

    /**
     * method to convert deadline to a string with time interpreted by the chat bot.
     */
    String makeStorageString() {
        return ("D | " + (isDone ? "1" : "0") + " | " + this.task + " | " + this.time.toString());
    }

    /**
     * overridden method to give the String representation of the task.
     *
     * @return String representation of the deadline to be printed on screen.
     */
    @Override
    public String toString() {
        return ("D | " + (isDone ? "1" : "0") + " | " + this.task + " | " + time.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
