package task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.ui.Ui;

/**
 * Task abstract class.
 */
public abstract class Task {
    protected final String description;
    protected boolean isDone;

    /**
     * Constructor.
     *
     * @param input Description of task.
     * @param isDone Whether the task is complete.
     */
    public Task(String input, boolean isDone) {
        description = input;
        this.isDone = isDone;
    }

    /**
     * Toggles completion of Task.
     *
     * @return New status of Task.
     */
    public String toggleDone() {
        isDone = !isDone;
        return Ui.messageToggleDone(isDone, this);
    }

    /**
     * Obtains the description of the task.
     *
     * @return Task Description.
     */
    public String getDescription() {
        return description;
    }

    public abstract LocalDate getDate();

    public abstract String getTime();

    //TODO
    protected static boolean checkInvalidTime(String time) {
        if (time.equals("")) return false;

        int timeInt = Integer.parseInt(time);
        return ( timeInt >= 2400  || timeInt < 0);
    }

    /**
     * Represents string of Task.
     *
     * @return Task display.
     */
    @Override
    public String toString() {
        String checkBox = isDone
                ? "[X] "
                : "[ ] ";
        return checkBox + description;
    }

    /**
     * Returns the string representation of Task to be used for saving.
     *
     * @return Save string.
     */
    public abstract String saveString();

    /**
     * Checks if the task falls on a given date (if applicable).
     *
     * @param date Date to check.
     * @return Whether task is tagged to the passed date.
     */
    public abstract boolean isDate(LocalDate date);
}
