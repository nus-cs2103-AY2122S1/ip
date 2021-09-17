package duke.tasks;

import duke.Response;
import duke.Ui;
import duke.exceptions.MissingDueDateDescriptionException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class that extends from task and has additional class variables
 * toCompleteBy which is the due date, the task type and time it is due by.
 */
public class Deadline extends Task {
    protected static final int MAX_SPLIT_LIMIT = 2;
    protected LocalDate toCompleteBy;
    protected String taskType;
    protected String time;

    /**
     * Public constructor of the class that takes in description,
     * due date and time it is due by.
     *
     * @param description The text description of the deadline task.
     * @param toCompleteBy The due date of the deadline.
     * @param time The time that it should be completed by.
     */
    public Deadline(String description, LocalDate toCompleteBy, String time) {
        super(description);
        this.toCompleteBy = toCompleteBy;
        this.taskType = "D";
        this.time = time;
    }

    /**
     * Marks Deadline as done, overrides the method in Task class.
     */
    @Override
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Gets the description of the deadline task that comes after the keyword deadline.
     *
     * @param input The string input provided by the user.
     * @return Description of the deadline task that comes after the keyword deadline
     * @throws MissingDueDateDescriptionException
     */
    public static String getDeadlineDescription(String input) throws MissingDueDateDescriptionException {
        String[] strArr = input.split(" /by", MAX_SPLIT_LIMIT);
        assert strArr.length == MAX_SPLIT_LIMIT : "Missing due date with format \"/by yyyy-mm-dd time\"";
        if (strArr.length < MAX_SPLIT_LIMIT) {
            Ui.showMissingDeadline();
            Response.showMissingDeadline();
            throw new MissingDueDateDescriptionException();
        } else {
            return strArr[0];
        }
    }

    /**
     * Gets the date the deadline is due by.
     *
     * @param input The string input provided by the user.
     * @return Date the deadline is due by.
     */
    public static LocalDate getDate(String input) {
        String[] strArr = input.split("/by ", MAX_SPLIT_LIMIT); // {desc, yyyy-mm-dd time}
        assert strArr.length == 2: "Missing date";
        String[] arr = strArr[1].split(" ", MAX_SPLIT_LIMIT); // {yyyy-mm-dd, time}
        assert arr.length == 2: "Missing time";
        return LocalDate.parse(arr[0]);
    }

    /**
     * Gets the time the deadline is due by.
     *
     * @param input The string input provided by the user.
     * @return Time the deadline is due by.
     */
    public static String getTime(String input) {
        String[] strArr = input.split("/by ", MAX_SPLIT_LIMIT); // {desc, yyyy-mm-dd time}
        assert strArr.length == 2: "Missing date";
        String[] arr = strArr[1].split(" ", MAX_SPLIT_LIMIT); // {yyyy-mm-dd, time}
        assert arr.length == 2: "Missing time";
        return arr[1];
    }

    /**
     * Converts the task into a string in the right format.
     *
     * @return String of the task in the right format.
     */
    @Override
    public String toString() {
        return "[" + this.taskType + "]" + super.toString() +  " (by: "
                + this.toCompleteBy.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ", " + this.time + ")";
    }
}
