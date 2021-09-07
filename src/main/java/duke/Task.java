package duke;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * This class contains methods for storing information about the various tasks found in a Tasklist.
 */
public class Task {
    protected boolean isDone;
    protected String item;
    protected String hasCross = "[X]";
    protected String hasNoCross = "[]";
    protected LocalDate date;

    public Task(String input) {
        isDone = false;
        item = input;
    }

    /**
     * Sets a task in the tasklist to completed.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Saves the string input of the date into LocalDate format.
     * @param date String input by user.
     */
    protected void parseDate(String date) {
        int[] dateArgs = Arrays.stream(date.split("-")).mapToInt(Integer::parseInt).toArray();
        this.date = LocalDate.of(dateArgs[0], dateArgs[1], dateArgs[2]);
    }

    @Override
    public String toString() {
        if (isDone) {
            return hasCross + " " + item;
        } else {
            return hasNoCross + " " + item;
        }
    }
}
