package tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exception.InvalidDateTimeException;
import exception.InvalidFormatInStorageException;
import exception.InvalidTaskFormatException;
import type.CommandTypeEnum;
import type.DatetimeTypeEnum;
import type.TaskIconTypeEnum;

/**
 * Encapsulates a task with a deadline.
 * It inherits from `DukeTask`.
 */
public class DeadlineTask extends Task {
    private static final String TIME_SPLITTER_INPUT = "/by";
    private static final String TIME_SPLITTER_DATA = "\\(by:";
    private LocalDateTime deadline;

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
    public static DeadlineTask createTask(String description) throws
            InvalidTaskFormatException,
            InvalidDateTimeException {
        String[] actionAndDateTimeDescriptions = splitActionAndTime(description, TIME_SPLITTER_INPUT);
        validateCorrectNumberOfParts(2, actionAndDateTimeDescriptions, CommandTypeEnum.DEADLINE);

        String actionDescription = actionAndDateTimeDescriptions[0];
        String dateTimeDescription = actionAndDateTimeDescriptions[1];
        LocalDateTime deadline = changeDateTimeStringToDateTime(DatetimeTypeEnum.INPUT.toString(), dateTimeDescription);

        return new DeadlineTask(actionDescription, false, deadline);
    }

    /**
     * Formats the task in string form, displaying the task type, status, description and deadline.
     *
     * @return Task in a displayed string format.
     */
    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DatetimeTypeEnum.OUTPUT.toString());
        String formattedDeadline = this.deadline.format(dateTimeFormatter);
        return String.format("[%s]%s (by: %s)",
                TaskIconTypeEnum.DEADLINE.toString(),
                super.toString(),
                formattedDeadline
        );
    }

    /**
     * Creates an app representation of a deadline task from the storage representation of the task.
     *
     * @param description Storage representation of a deadline task.
     * @return App representation of a deadline task.
     */
    public static DeadlineTask createTaskFromStoredString(String description) throws InvalidFormatInStorageException {
        try {
            boolean isDone = Task.isStorageTaskDone(description);

            int descriptionStartPos = 3;
            String[] actionAndDateTimeDescriptions = splitActionAndTime(
                    description.substring(descriptionStartPos),
                    TIME_SPLITTER_DATA
            );
            validateCorrectNumberOfParts(2, actionAndDateTimeDescriptions, CommandTypeEnum.DEADLINE);

            String actionDescription = actionAndDateTimeDescriptions[0];
            String deadlineWithClosingBracket = actionAndDateTimeDescriptions[1];
            String deadlineString = deadlineWithClosingBracket.substring(0, deadlineWithClosingBracket.length() - 1);
            LocalDateTime deadline = changeDateTimeStringToDateTime(DatetimeTypeEnum.OUTPUT.toString(), deadlineString);

            return new DeadlineTask(actionDescription, isDone, deadline);
        } catch (InvalidDateTimeException | InvalidTaskFormatException e) {
            throw new InvalidFormatInStorageException(e.getMessage() + ": " + description);
        }
    }

    @Override
    protected boolean isDuplicateOf(Task task) {
        // A different type of task is definitely not a duplicate
        if (!(task instanceof DeadlineTask)) {
            return false;
        }

        // A different description means the task is definitely not a duplicate
        if (!this.isSameDescription(task)) {
            return false;
        }

        DeadlineTask deadlineTask = (DeadlineTask) task;
        return this.deadline.equals(deadlineTask.deadline);
    }

    private static LocalDateTime changeDateTimeStringToDateTime(String dateTimeFormat, String dateTimeString)
            throws InvalidDateTimeException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimeFormat);

        try {
            return LocalDateTime.from(dateTimeFormatter.parse(dateTimeString));
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException(dateTimeFormat);
        }
    }
}
