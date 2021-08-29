package seedu.duke;

import seedu.duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Represents a DateTimeManager. A <code>DateTimeManager</code> object
 * handles the parsing of DateTime strings in the given format, as well
 * track and updates tasks that occur on specific days.
 */
public class DateTimeManager {
    private DateTimeFormatter formatter;

    public DateTimeManager(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    /**
     * Updates the given HashMap to reflect the tasks happening on a certain date.
     *
     * @param dateTasks The hashmap to be updated.
     * @param date The date requested.
     * @param task The task to be classified under the requested date.
     */
    public void updateDateTasks(HashMap<LocalDate, ArrayList<Task>> dateTasks,
                                 LocalDate date, Task task) {
        ArrayList<Task> tasksOnDate = dateTasks.getOrDefault(date, new ArrayList<>());
        tasksOnDate.add(task);
        dateTasks.put(date, tasksOnDate);
    }

    /**
     * Returns a LocalDate representation of the string
     * representation of the date.
     *
     * @param dateTime The string to be parsed as a LocalDate object.
     * @return The LocalDate object of the string given.
     * @throws DukeException If the string is not given in the valid format
     * as specified by the DateTimeFormatter.
     */
    public LocalDate parseDateTime(String dateTime) throws DukeException {
        try {
            LocalDate date = LocalDate.parse(dateTime, formatter);
            return date;
        } catch (DateTimeParseException e) {
            throw new DukeException("Cannot read date.");
        }
    }
}
