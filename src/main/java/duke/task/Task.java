package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

/** Task class that makes up objects in the TaskList */
public class Task {

    private String description;
    private boolean isDone;
    private LocalDate date = null;

    /* Alphabetical representation of the Task types */
    public final static String TODO_ALPHABET = "T";
    public final static String DEADLINE_ALPHABET = "D";
    public final static String EVENT_ALPHABET = "E";

    /* String representation of whether the task is done, for saving into the .txt file */
    public final static String DONE_STRING = "1";
    public final static String NOT_DONE_STRING = "0";

    /* Index of the attributes saved in the .txt file */
    public final static int TASK_TYPE_INDEX = 0;
    public final static int TASK_DONE_INDEX = 1;
    public final static int TASK_DESCRIPTION_INDEX = 2;
    public final static int TASK_DATE_INDEX = 3;

    /* The delimiter between the attributes for the task in the .txt file */
    public final static String STORAGE_DELIMITER = ",";

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

    private String getDoneString() { return isDone ? DONE_STRING : NOT_DONE_STRING; }

    /**
     * Formats the task data for saving in tasks.txt
     */
    public String toFileData() { return String.join(STORAGE_DELIMITER, getDoneString(), description); }

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

    public LocalDate getDate() {
        return date;
    }

    /**
     * Wraps the task alphabet in square brackets
     *
     * @param alphabet Alphabetical representation of the Task
     * @return The alphabet wrapped in square brackets
     */
    public String wrapTaskAlphabet(String alphabet) {
        return String.format("[%s]", alphabet);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getDoneSymbol(), description);
    }
}
