package tasklist;

import exception.InvalidDateTimeException;
import exception.InvalidTaskTimeFormatException;
import exception.InvalidTaskTypeException;
import type.DukeCommandTypeEnum;

/**
 * Encapsulates a task containing a description and status.
 */
public class Task {
    protected static final String STATUS_ICON_DONE = "X";
    protected static final String STATUS_ICON_NOT_DONE = " ";
    private String description;
    private boolean isDone;

    /**
     * Constructor to instantiate a `entity.list.DukeTask`.
     * The isDone field is set to false by default as new tasks should not be done yet.
     *
     * @param description describes what the task is
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Creates a specific `entity.list.DukeTask` based on the prefix in the user input.
     * Supported types of tasks include todo, deadline and event.
     *
     * @param description the full user input that may or may not be a valid type of task
     * @return A new `entity.list.DukeTask`
     * @throws InvalidTaskTypeException when the type of task is not recognised
     * @throws InvalidTaskTimeFormatException when a task does not have valid time inputs
     */
    public static Task createTask(String description, DukeCommandTypeEnum actionType)
            throws InvalidTaskTypeException, InvalidTaskTimeFormatException, InvalidDateTimeException {
        // A valid task is either a to-do, deadline or event

        if (actionType.equals(DukeCommandTypeEnum.TODO)) {
            return TodoTask.createTask(description);
        }

        if (actionType.equals(DukeCommandTypeEnum.DEADLINE)) {
            return DeadlineTask.createTask(description);
        }

        if (actionType.equals(DukeCommandTypeEnum.EVENT)) {
            return EventTask.createTask(description);
        }

        throw new InvalidTaskTypeException(actionType);
    }

    /**
     * Utility method to split tasks into action and time parts.
     *
     * @param descriptionWithTime the description of a task including time information
     * @param splitter the regex to split the description by
     * @return a string array whose first item is the action description and second item is the time
     */
    public static String[] splitActionAndTime(String descriptionWithTime, String splitter) {
        String[] splitParts = descriptionWithTime.split(splitter);

        // Trim split parts to remove whitespace before and after
        for (int i = 0; i < splitParts.length; i++) {
            splitParts[i] = splitParts[i].trim();
        }

        return splitParts;
    }

    /**
     * Method to mark a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    private String getStatusIcon() {
        return (this.isDone ? STATUS_ICON_DONE : STATUS_ICON_NOT_DONE);
    }

    /**
     * Formats the task in string form, displaying the task status and description.
     *
     * @return the task in a displayed string format
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    /**
     * Creates an app representation of a task from the storage representation of the task.
     *
     * @param fullDescription Storage representation of the task including the task type.
     * @return App representation of the task.
     */
    public static Task createTaskFromStoredString(String fullDescription) {
        String trimmedFullDescription = fullDescription.trim();
        char taskType = trimmedFullDescription.charAt(1);
        String descriptionWithoutTaskType = fullDescription.substring(3);

        switch (taskType) {
        case 'T':
            return TodoTask.createTaskFromStoredString(descriptionWithoutTaskType);
        case 'D':
            return DeadlineTask.createTaskFromStoredString(descriptionWithoutTaskType);
        case 'E':
            return EventTask.createTaskFromStoredString(descriptionWithoutTaskType);
        default:
            return null;
        }
    }

    /**
     * Checks if the task description contains a keyword.
     *
     * @param keyword Keyword to search for.
     * @return True if task description contains the keyword, false otherwise.
     */
    protected boolean contains(String keyword) {
        return this.description.contains(keyword);
    }
}
