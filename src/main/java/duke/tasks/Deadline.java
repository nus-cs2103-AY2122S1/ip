package duke.tasks;

import duke.Ui;
import duke.exceptions.MissingDueDateDescriptionException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class that extends from task and has additional class variables
 * toCompleteBy which is the due date, the task type and time it is due by
 */
public class Deadline extends Task {
    protected LocalDate toCompleteBy;
    protected String taskType;
    protected String time; //todo

    /**
     * Public constructor of the class that takes in description,
     * due date and time it is due by
     *
     * @param description
     * @param toCompleteBy
     * @param time
     */
    public Deadline(String description, LocalDate toCompleteBy, String time) {
        super(description);
        this.toCompleteBy = toCompleteBy;
        this.taskType = "D";
        this.time = time;
    }

    /**
     * Gets the task type of an instance
     *
     * @return D meaning Deadline
     */
    @Override
    public String getTaskType() {
        return this.taskType;
    }

    /**
     * Marks Deadline as done, overrides the method in Task class
     */
    @Override
    public void markAsDone() {
        this.isDone = true;
        System.out.println(this.toString());
    }

    /**
     * Gets the description of the deadline task that comes after the keyword deadline
     * @param input
     * @return Description of the deadline task that comes after the keyword deadline
     * @throws MissingDueDateDescriptionException
     */
    public static String getDeadlineDescription(String input) throws MissingDueDateDescriptionException {
        String[] strArr = input.split(" /by", 2);
        if (strArr.length < 2) {
            Ui.missingDeadline();
            throw new MissingDueDateDescriptionException();
        } else {
            return strArr[0];
        }
    }

    /**
     * Gets the due date and time from the users input
     *
     * @param input
     * @return Due date and time from the users input
     */
    public static LocalDate getDueDate(String input) {
        String[] strArr = input.split("/by ", 2);
        return LocalDate.parse(strArr[1]);
    }

    /**
     * Gets the date the deadline is due by
     * @param input
     * @return Date the deadline is due by
     */
    public static LocalDate getDate(String input) {
        String[] strArr = input.split("/by ", 2); // {desc, yyyy-mm-dd time}
        String[] arr = strArr[1].split(" ", 2); // {yyyy-mm-dd, time}
        return LocalDate.parse(arr[0]);
    }

    /**
     * Gets the time the deadline is due by
     * @param input
     * @return Time the deadline is due by
     */
    public static String getTime(String input) {
        String[] strArr = input.split("/by ", 2); // {desc, yyyy-mm-dd time}
        String[] arr = strArr[1].split(" ", 2); // {yyyy-mm-dd, time}
        return arr[1];
    }

    /**
     * Converts the task into a string in the right format
     * @return String of the task in the right format
     */
    @Override
    public String toString() {
        return "[" + this.taskType + "]" + super.toString() +  " (by: "
                + this.toCompleteBy.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ", " + this.time + ")";
    }
}
