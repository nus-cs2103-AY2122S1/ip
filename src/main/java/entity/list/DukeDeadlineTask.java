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
    private static String TIME_SPLITTER_INPUT = "/by";
    private static String TIME_SPLITTER_DATA = "\\(by:";
    private static String DEADLINE_INPUT_FORMAT = "dd-MM-yyyy HHmm";
    private static String DEADLINE_OUTPUT_FORMAT = "MMM d yyyy HHmm";
    private LocalDateTime deadline;

    private DukeDeadlineTask(String description, boolean isDone, LocalDateTime deadline) {
        super(description, isDone);
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
                DukeDeadlineTask.TIME_SPLITTER_INPUT
        );

        if (splitPartsUsingBy.length != 2) {
            throw new InvalidTaskTimeFormatException(DukeActionTypeEnum.DEADLINE.toString(), DukeDeadlineTask.TIME_SPLITTER_INPUT);
        }

        String actionDescription = splitPartsUsingBy[0];
        String dateTimeDescription = splitPartsUsingBy[1];
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DEADLINE_INPUT_FORMAT);

        try {
            LocalDateTime deadline = LocalDateTime.from(dateTimeFormatter.parse(dateTimeDescription));
            return new DukeDeadlineTask(actionDescription, false, deadline);
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

    public static DukeDeadlineTask createTaskFromStoredString(String description) {
        String statusIcon = description.substring(1, 2);
        boolean isDone = false;
        if (statusIcon.equals(DukeTask.STATUS_ICON_DONE)) {
            isDone = true;
        }

        String[] splitPartsUsingBy = DukeTask.splitActionAndTime(
                description.substring(3),
                TIME_SPLITTER_DATA
        );

        String actionDescription = splitPartsUsingBy[0];
        String deadlineWithClosingBracket = splitPartsUsingBy[1];
        String deadlineString = deadlineWithClosingBracket.substring(0, deadlineWithClosingBracket.length() - 1);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DEADLINE_OUTPUT_FORMAT);
        LocalDateTime deadline = LocalDateTime.from(dateTimeFormatter.parse(deadlineString));

        return new DukeDeadlineTask(actionDescription, isDone, deadline);
    }
}
