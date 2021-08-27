package tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exception.InvalidDateTimeException;
import exception.InvalidTaskTimeFormatException;
import type.DukeCommandTypeEnum;

/**
 * Encapsulates a task with a deadline.
 * It inherits from `entity.list.DukeTask`.
 */
public class DeadlineTask extends Task {
    private static final String TIME_SPLITTER_INPUT = "/by";
    private static final String TIME_SPLITTER_DATA = "\\(by:";
    private static final String DEADLINE_INPUT_FORMAT = "dd-MM-yyyy HHmm";
    private static final String DEADLINE_OUTPUT_FORMAT = "MMM d yyyy HHmm";
    private final LocalDateTime deadline;

    private DeadlineTask(String description, boolean isDone, LocalDateTime deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    /**
     * Processes the input string to create a deadline task with an action and deadline.
     *
     * @param description Input task string.
     * @return App representation of a task containing an action description and deadline information.
     */
    public static DeadlineTask createTask (String description) throws
            InvalidTaskTimeFormatException,
            InvalidDateTimeException {
        // Split the description into its action and time parts
        String[] splitPartsUsingBy = splitActionAndTime(
                description,
                DeadlineTask.TIME_SPLITTER_INPUT
        );

        if (splitPartsUsingBy.length != 2) {
            throw new InvalidTaskTimeFormatException(
                    DukeCommandTypeEnum.DEADLINE.toString(),
                    DeadlineTask.TIME_SPLITTER_INPUT
            );
        }

        String actionDescription = splitPartsUsingBy[0];
        String dateTimeDescription = splitPartsUsingBy[1];
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DEADLINE_INPUT_FORMAT);

        try {
            LocalDateTime deadline = LocalDateTime.from(dateTimeFormatter.parse(dateTimeDescription));
            return new DeadlineTask(actionDescription, false, deadline);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException(DEADLINE_INPUT_FORMAT);
        }
    }

    /**
     * Formats the task in string form, displaying the task type, status, description and deadline.
     *
     * @return Task in a displayed string format.
     */
    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DEADLINE_OUTPUT_FORMAT);
        String formattedDeadline = this.deadline.format(dateTimeFormatter);
        return String.format("[D]%s (by: %s)", super.toString(), formattedDeadline);
    }

    /**
     * Creates an app representation of a deadline task from the storage representation of the task.
     *
     * @param description Storage representation of a deadline task.
     * @return App representation of a deadline task.
     */
    public static DeadlineTask createTaskFromStoredString(String description) {
        String statusIcon = description.substring(1, 2);
        boolean isDone = false;
        if (statusIcon.equals(STATUS_ICON_DONE)) {
            isDone = true;
        }

        String[] splitPartsUsingBy = splitActionAndTime(
                description.substring(3),
                TIME_SPLITTER_DATA
        );

        String actionDescription = splitPartsUsingBy[0];
        String deadlineWithClosingBracket = splitPartsUsingBy[1];
        String deadlineString = deadlineWithClosingBracket.substring(0, deadlineWithClosingBracket.length() - 1);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DEADLINE_OUTPUT_FORMAT);
        LocalDateTime deadline = LocalDateTime.from(dateTimeFormatter.parse(deadlineString));

        return new DeadlineTask(actionDescription, isDone, deadline);
    }
}
