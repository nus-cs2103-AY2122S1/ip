package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * An abstraction for Tasks.
 */
public abstract class Task {
    private boolean completed;
    protected final String REGEX_FOR_STORAGE = "_/_,_/_";

    public Task() {
        completed = false;
    }

    public void setCompleted() {
        completed = true;
    }

    /**
     * Retrieves the icon that marks whether a task is completed.
     * @return "X" if the task has been completed, else " ".
     */
    public String getStatusIcon() {
        return completed ? "X" : " ";
    }

    public int isCompleted() {
        return completed ? 1 : 0;
    }

    /**
     * Looks through time and replaces occurrences of dates in the YYYY-MM-DD format with the MMM DD YYYY format
     * and returns the new String.
     *
     * @param time A String which possibly contains a date in the YYYY-MM-DD format.
     * @return The new String with dates in the YYYY-MM-DD format changed to MMM DD YYYY format.
     */
    public String formatTime(String time) {
        final String REGEX = " ";
        String[] splittedTime = time.split(REGEX);
        // Loop through splittedTime and replace dates with the appropriate format
        for (int i = 0; i < splittedTime.length; i++) {
            try {
                LocalDate date = LocalDate.parse(splittedTime[i]);
                splittedTime[i] = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            } catch (DateTimeParseException e) {
                // splittedTime[i] is not in the date format, so do nothing
            }
        }
        return String.join(REGEX, splittedTime);
    }

    /**
     * Returns the storage format of the task.
     * @return The storage format of the task.
     */
    public abstract String toStorageFormat();

}
