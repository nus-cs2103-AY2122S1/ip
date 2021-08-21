package entity.list;

import exception.InvalidTaskTimeFormatException;
import type.DukeActionTypeEnum;

/**
 * Encapsulates a task with that will occur at a specified time period.
 * It inherits from `entity.list.DukeTask`.
 */
public class DukeEventTask extends DukeTask {
    private static String TIME_SPLITTER_INPUT = "/at";
    private static String TIME_SPLITTER_DATA = "\\(at:";
    private String time;

    private DukeEventTask(String description, boolean isDone, String time) {
        super(description, isDone);
        this.time = time;
    }

    /**
     * Processes the input string to create an event task with an action and time.
     *
     * @param description The input task string by the user
     * @return a `entity.list.DukeEventTask` containing an action description and time information
     */
    public static DukeEventTask createTask (String description) throws InvalidTaskTimeFormatException {
        // Split the description into its action and time parts
        String[] splitPartsUsingAt = DukeTask.splitActionAndTime(
                description,
                DukeEventTask.TIME_SPLITTER_INPUT
        );

        try {
            return new DukeEventTask(splitPartsUsingAt[0], false, splitPartsUsingAt[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidTaskTimeFormatException(
                    DukeActionTypeEnum.EVENT.toString(),
                    DukeEventTask.TIME_SPLITTER_INPUT
            );
        }
    }

    /**
     * Formats the task in string form, displaying the task type, status, description and time.
     *
     * @return the task in a displayed string format
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.time);
    }

    public static DukeEventTask createTaskFromStoredString(String description) {
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
        String timeWithClosingBracket = splitPartsUsingBy[1];
        String time = timeWithClosingBracket.substring(0, timeWithClosingBracket.length() - 1);

        return new DukeEventTask(actionDescription, isDone, time);
    }
}
