package tasklist;

import exception.InvalidFormatInStorageException;
import exception.InvalidTaskFormatException;
import type.CommandTypeEnum;
import type.TaskIconTypeEnum;

/**
 * Encapsulates a task with that will occur at a specified time period.
 * It inherits from `DukeTask`.
 */
public class EventTask extends Task {
    private static final String TIME_SPLITTER_INPUT = "/at";
    private static final String TIME_SPLITTER_DATA = "\\(at:";
    private String time;

    private EventTask(String description, boolean isDone, String time) {
        super(description, isDone);
        this.time = time;
    }

    /**
     * Processes the input string to create an event task with an action and time.
     *
     * @param description Input task string.
     * @return App representation of a task containing an action description and time information.
     */
    public static EventTask createTask (String description) throws InvalidTaskFormatException {
        // Split the description into its action and time parts
        String[] actionAndTimeDescriptions = splitActionAndTime(
                description,
                EventTask.TIME_SPLITTER_INPUT
        );
        validateCorrectNumberOfParts(2, actionAndTimeDescriptions, CommandTypeEnum.EVENT);
        String actionDescription = actionAndTimeDescriptions[0];
        String timeDescription = actionAndTimeDescriptions[1];

        return new EventTask(actionDescription, false, timeDescription);
    }

    /**
     * Formats the task in string form, displaying the task type, status, description and time.
     *
     * @return Task in a displayed string format.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (at: %s)", TaskIconTypeEnum.EVENT.toString(), super.toString(), this.time);
    }

    /**
     * Creates an app representation of an event task from the storage representation of the task.
     *
     * @param description Storage representation of an event task.
     * @return App representation of an event task.
     */
    public static EventTask createTaskFromStoredString(String description) throws InvalidFormatInStorageException {
        try {
            boolean isDone = Task.isStorageTaskDone(description);

            int descriptionStartPos = 3;
            String[] actionAndTimeDescriptions = splitActionAndTime(
                    description.substring(descriptionStartPos),
                    TIME_SPLITTER_DATA
            );
            validateCorrectNumberOfParts(2, actionAndTimeDescriptions, CommandTypeEnum.EVENT);

            String actionDescription = actionAndTimeDescriptions[0];
            String timeWithClosingBracket = actionAndTimeDescriptions[1];
            String time = timeWithClosingBracket.substring(0, timeWithClosingBracket.length() - 1);

            return new EventTask(actionDescription, isDone, time);
        } catch (InvalidTaskFormatException e) {
            throw new InvalidFormatInStorageException(e.getMessage() + ": " + description);
        }

    }

    @Override
    protected boolean isDuplicateOf(Task task) {
        // A different type of task is definitely not a duplicate
        if (!(task instanceof EventTask)) {
            return false;
        }

        // A different description means the task is definitely not a duplicate
        if (!this.isSameDescription(task)) {
            return false;
        }

        EventTask eventTask = (EventTask) task;
        return this.time.equals(eventTask.time);
    }
}
