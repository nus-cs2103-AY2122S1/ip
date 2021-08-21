package entity.list;

import exception.InvalidTaskTimeFormatException;
import type.DukeActionTypeEnum;

/**
 * Encapsulates a task with a deadline.
 * It inherits from `entity.list.DukeTask`.
 */
public class DukeDeadlineTask extends DukeTask {
    private static String TIME_SPLITTER_INPUT = "/by";
    private static String TIME_SPLITTER_DATA = "\\(by:";
    private String deadline;

    private DukeDeadlineTask(String description, boolean isDone, String deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    /**
     * Processes the input string to create a deadline task with an action and deadline.
     *
     * @param description The input task string by the user
     * @return a `entity.list.DukeDeadlineTask` containing an action description and deadline information
     */
    protected static DukeDeadlineTask createTask (String description) throws InvalidTaskTimeFormatException {
        // Split the description into its action and time parts
        String[] splitPartsUsingBy = DukeTask.splitActionAndTime(
                description,
                DukeDeadlineTask.TIME_SPLITTER_INPUT
        );

        try {
            return new DukeDeadlineTask(splitPartsUsingBy[0], false, splitPartsUsingBy[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidTaskTimeFormatException(
                    DukeActionTypeEnum.DEADLINE.toString(),
                    DukeDeadlineTask.TIME_SPLITTER_INPUT
            );
        }
    }

    /**
     * Formats the task in string form, displaying the task type, status, description and deadline.
     *
     * @return the task in a displayed string format
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline);
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
        String deadline = deadlineWithClosingBracket.substring(0, deadlineWithClosingBracket.length() - 1);

        return new DukeDeadlineTask(actionDescription, isDone, deadline);
    }
}
