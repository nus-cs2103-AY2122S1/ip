package seedu.duke;

import seedu.duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;

class DateTimeManager {
    DateTimeFormatter formatter;

    public DateTimeManager(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    public void updateDateTasks(HashMap<LocalDate, ArrayList<Task>> dateTasks,
                                 LocalDate date, Task task) {
        ArrayList<Task> tasksOnDate = dateTasks.getOrDefault(date, new ArrayList<>());
        tasksOnDate.add(task);
        dateTasks.put(date, tasksOnDate);
    }

    public LocalDate parseDateTime(String dateTime) throws DukeException {
        try {
            LocalDate date = LocalDate.parse(dateTime, formatter);
            return date;
        } catch (DateTimeParseException e) {
            throw new DukeException("Cannot read date.");
        }
    }
}
