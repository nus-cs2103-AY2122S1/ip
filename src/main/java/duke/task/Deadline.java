package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.InvalidParamException;

public class Deadline extends Task{

    private static final String SEPARATOR = " /by ";

    private String by;
    private LocalDate date;

    /**
     * A constructor for this deadline Task.
     *
     * @param description the description of what the task is.
     * @param by the specific date/time that this task has to be done by.
     */
    public Deadline(String description, String by) throws InvalidParamException {
        super(description);
        this.by = by;

        try {
            this.date = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new InvalidParamException("\n\nThe deadline should be a valid date in the form: yyyy-mm-dd\n"
                    + "i.e. 2021-12-25");
        }
    }

    /**
     * Returns a deadline task based on the given description.
     *
     * @param input the string containing the deadline task description.
     * @return the deadline task constructed from the given description.
     * @throws InvalidParamException if the description does not contain the appropriate information.
     */
    public static Task setDeadline(String input) throws InvalidParamException {
        String[] deadlineParams = input.split(SEPARATOR);
        if (deadlineParams.length != 2) {
            throw new InvalidParamException("Please include the deadline of the task after\n"
                    + "a task description using a '/by' (only once).\n"
                    + "i.e. deadline return book /by 2021-12-25");
        }
        Task deadline = new Deadline(deadlineParams[0], deadlineParams[1]);
        return deadline;
    }

    /**
     * Returns the string representation of this Task, which follows the following format:
     * [D][Task status] Task Description (by: Task deadline)
     *
     * @return string representation of this Task, which is the type of task (Deadline),
     *         its status, its description, and its deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    public String getBy() {
        return this.by;
    }

    public static String getSeparator() {
        return SEPARATOR;
    }
}
