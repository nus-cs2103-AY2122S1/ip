package duke.task;

import duke.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

public class Task {

    private String description;
    private boolean isDone;
    private LocalDate date = null;

    public Task(String description, boolean done) {
        this.description = description;
        this.isDone = done;
    }

    public Task(String description, String date, boolean done) throws DukeException {
        this.description = description;
        this.isDone = done;
        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid Date format!");
        }
    }

    /**
     * Marks the task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    private String getDoneSymbol() {
        return isDone ? "X" : " ";
    }

    private String getDoneString() { return isDone ? "1" : "0"; }

    /**
     * Formats the task data for saving in tasks.txt
     */
    public String toFileData() { return String.format("%s,%s", getDoneString(), description); }

    /**
     * Gets the date of the task in the format of DD Month-Name YYYY
     * e.g. 1 January 2021
     *
     * @return String representation of the date in the format
     */
    public String getDateString() {
        return this.date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
    }

    /**
     * @return String of the date in the format of YYYY-MM-DD
     */
    public String dateToString() {
        return this.date.toString();
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getDoneSymbol(), description);
    }
}
