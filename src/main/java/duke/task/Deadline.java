package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.InvalidParamException;

/**
 * Represents a Deadline type Task.
 */
public class Deadline extends Task {

    /** String that Deadline uses to distinguish the description from the deadline date in the input String */
    private static final String SEPARATOR = " /by ";

    /** String containing the deadline date that this Deadline is due on*/
    private String by;
    /** Date that this Deadline is due on */
    private LocalDate date;

    /**
     * Constructs a Deadline Task with the given description and information.
     *
     * @param description Description of the Deadline.
     * @param by The specific date that this Deadline is due on.
     * @throws InvalidParamException If the description does not contain the appropriate information or format.
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
     * Returns the string representation of this Deadline, which follows the following format:
     * [D][Task status] Task Description (by: Task deadline)
     *
     * @return String representation of this Deadline, which consist of the type of task (Deadline),
     *         its status, its description, and its deadline date.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * Returns a Deadline Task based on the given description.
     *
     * @param input String containing the Deadline description.
     * @return Deadline constructed from the given description.
     * @throws InvalidParamException If the description does not contain the appropriate information or format.
     */
    public static Task setDeadline(String input) throws InvalidParamException {
        String[] deadlineParams = input.split(SEPARATOR);
        if (deadlineParams.length != 2) {
            throw new InvalidParamException("Please include the deadline of the task after\n"
                    + "a task description using a '/by' (only once).\n"
                    + "i.e. deadline return book /by 2021-12-25");
        }
        return new Deadline(deadlineParams[0], deadlineParams[1]);
    }

    /**
     * Returns the date that the Deadline is due on.
     *
     * @return String containing the date that this Deadline is due on.
     */
    public String getBy() {
        return this.by;
    }

    /**
     * Returns the separator string used to distinguish the description from the Deadline date in the input String.
     *
     * @return Separator string.
     */
    public static String getSeparator() {
        return SEPARATOR;
    }
}
