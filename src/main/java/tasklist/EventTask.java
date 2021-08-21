package tasklist;

import exception.InvalidTaskTimeFormatException;
import type.DukeCommandTypeEnum;

/**
 * Encapsulates a task with that will occur at a specified time period.
 * It inherits from `entity.list.DukeTask`.
 */
public class EventTask extends Task {
    private static String TIME_SPLITTER_INPUT = "/at";
    private static String TIME_SPLITTER_DATA = "\\(at:";
    private String time;

    private EventTask(String description, boolean isDone, String time) {
        super(description, isDone);
        this.time = time;
    }

    /**
     * Processes the input string to create an event task with an action and time.
     *
     * @param description The input task string by the user
     * @return a `entity.list.DukeEventTask` containing an action description and time information
     */
    public static EventTask createTask (String description) throws InvalidTaskTimeFormatException {
        // Split the description into its action and time parts
        String[] splitPartsUsingAt = splitActionAndTime(
                description,
                EventTask.TIME_SPLITTER_INPUT
        );

        try {
            return new EventTask(splitPartsUsingAt[0], false, splitPartsUsingAt[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidTaskTimeFormatException(
                    DukeCommandTypeEnum.EVENT.toString(),
                    EventTask.TIME_SPLITTER_INPUT
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

    public static EventTask createTaskFromStoredString(String description) {
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
        String timeWithClosingBracket = splitPartsUsingBy[1];
        String time = timeWithClosingBracket.substring(0, timeWithClosingBracket.length() - 1);

        return new EventTask(actionDescription, isDone, time);
    }
}
