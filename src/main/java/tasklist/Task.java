package tasklist;

import exception.InvalidDateTimeException;
import exception.InvalidFormatInStorageException;
import exception.InvalidTaskFormatException;
import exception.InvalidTaskTypeException;
import type.CommandTypeEnum;
import type.TaskIconTypeEnum;

/**
 * Encapsulates a task containing a description and status.
 */
public abstract class Task {
    protected static final int LENGTH_OF_STATUS_ICON = 1;
    protected static final String STATUS_ICON_DONE = "X";
    protected static final String STATUS_ICON_NOT_DONE = " ";
    private String description;
    private boolean isDone;

    /**
     * Instantiates a `entity.list.DukeTask`.
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
     * @return `DukeTask`
     * @throws InvalidTaskTypeException when the type of task is not recognised
     * @throws InvalidTaskFormatException when a task does not have valid time inputs
     */
    public static Task createTask(String description, CommandTypeEnum commandType)
            throws InvalidTaskTypeException, InvalidTaskFormatException, InvalidDateTimeException {
        switch (commandType) {
        case TODO:
            return TodoTask.createTask(description);
        case DEADLINE:
            return DeadlineTask.createTask(description);
        case EVENT:
            return EventTask.createTask(description);
        default:
            throw new InvalidTaskTypeException(commandType);
        }
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
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
    public static Task createTaskFromStoredString(String fullDescription) throws InvalidFormatInStorageException {
        String trimmedFullDescription = fullDescription.trim();

        int taskTypeStartPos = 1;
        String taskType = trimmedFullDescription.substring(taskTypeStartPos, taskTypeStartPos + 1);
        TaskIconTypeEnum taskIconTypeEnum = TaskIconTypeEnum.getEnum(taskType);
        if (taskIconTypeEnum == null) {
            throw new InvalidFormatInStorageException(fullDescription);
        }

        int descriptionStartPos = 3;
        String descriptionWithoutTaskType = fullDescription.substring(descriptionStartPos);

        switch (taskIconTypeEnum) {
        case DEADLINE:
            return DeadlineTask.createTaskFromStoredString(descriptionWithoutTaskType);
        case EVENT:
            return EventTask.createTaskFromStoredString(descriptionWithoutTaskType);
        case TODO:
            return TodoTask.createTaskFromStoredString(descriptionWithoutTaskType);
        default:
            assert false : "taskTypeEnum should be valid and handled if it reaches the switch block";
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

    /**
     * Split tasks into action and time parts.
     *
     * @param descriptionWithTime the description of a task including time information
     * @param splitter the regex to split the description by
     * @return a string array whose first item is the action description and second item is the time
     */
    protected static String[] splitActionAndTime(String descriptionWithTime, String splitter) {
        String[] splitParts = descriptionWithTime.split(splitter);

        // Trim split parts to remove whitespace before and after
        for (int i = 0; i < splitParts.length; i++) {
            splitParts[i] = splitParts[i].trim();
        }

        return splitParts;
    }

    protected static void validateCorrectNumberOfParts(
            int expectedNumOfParts,
            String[] splitParts,
            CommandTypeEnum commandType) throws InvalidTaskFormatException {
        if (splitParts.length != expectedNumOfParts) {
            throw new InvalidTaskFormatException(commandType);
        }
    }

    protected boolean isSameDescription(Task task) {
        return this.description.equals(task.description);
    }

    protected abstract boolean isDuplicateOf(Task task);

    protected static boolean isStorageTaskDone(String description) {
        int statusIconStartPos = 1;
        String statusIcon = description.substring(statusIconStartPos, statusIconStartPos + LENGTH_OF_STATUS_ICON);
        return statusIcon.equals(STATUS_ICON_DONE);
    }

    private String getStatusIcon() {
        return (this.isDone ? STATUS_ICON_DONE : STATUS_ICON_NOT_DONE);
    }
}
