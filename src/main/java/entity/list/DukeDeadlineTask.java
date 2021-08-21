package entity.list;

import exception.InvalidTaskTimeFormatException;
import exception.InvalidDateTimeException;
import type.DukeActionTypeEnum;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates a task with a deadline.
 * It inherits from `entity.list.DukeTask`.
 */
public class DukeDeadlineTask extends DukeTask {
    private static String TIME_SPLITTER = "/by";
    private static String DEADLINE_INPUT_FORMAT = "dd-MM-yyyy HHmm";
    private static String DEADLINE_OUTPUT_FORMAT = "MMM d yyyy HHmm";
    private LocalDateTime deadline;

    private DukeDeadlineTask(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Processes the input string to create a deadline task with an action and deadline.
     *
     * @param description The input task string by the user
     * @return a `entity.list.DukeDeadlineTask` containing an action description and deadline information
     */
    public static DukeDeadlineTask createTask (String description) throws InvalidTaskTimeFormatException, InvalidDateTimeException {
        // Split the description into its action and time parts
        String[] splitPartsUsingBy = DukeTask.splitActionAndTime(
                description,
                DukeDeadlineTask.TIME_SPLITTER
        );

        if (splitPartsUsingBy.length != 2) {
            throw new InvalidTaskTimeFormatException(DukeActionTypeEnum.DEADLINE.toString(), DukeDeadlineTask.TIME_SPLITTER);
        }

        String actionDescription = splitPartsUsingBy[0];
        String dateTimeDescription = splitPartsUsingBy[1];
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DEADLINE_INPUT_FORMAT);

        try {
            LocalDateTime deadline = LocalDateTime.from(dateTimeFormatter.parse(dateTimeDescription));
            return new DukeDeadlineTask(actionDescription, deadline);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException(DEADLINE_INPUT_FORMAT);
        }
    }

    /**
     * Formats the task in string form, displaying the task type, status, description and deadline.
     *
     * @return the task in a displayed string format
     */
    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DEADLINE_OUTPUT_FORMAT);
        String formattedDeadline = this.deadline.format(dateTimeFormatter);
        return String.format("[D]%s (by: %s)", super.toString(), formattedDeadline);
    }
}
