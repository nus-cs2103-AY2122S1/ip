package tasklist;

import java.util.Arrays;

import exception.InvalidCommandFormatException;
import exception.InvalidDateTimeFormatException;
import exception.InvalidFormatInStorageException;
import exception.InvalidTaskTypeException;
import exception.InvalidTimePeriodException;
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
     * Instantiates a `Task`.
     *
     * @param description Description of the task.
     * @param isDone The status of a task, either done or not.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Creates a specific Task based on the prefix in the user input.
     *
     * @param description User input that may be a valid type of task.
     * @param commandType Command Type.
     * @return `Task`.
     * @throws InvalidTaskTypeException If the type of task is not recognised.
     * @throws InvalidCommandFormatException If the command is incorrectly formatted.
     * @throws InvalidDateTimeFormatException If there is an invalid date time.
     * @throws InvalidTimePeriodException If the start time is after the end time.
     */
    public static Task createTask(String description, CommandTypeEnum commandType) throws
            InvalidTaskTypeException,
            InvalidCommandFormatException,
            InvalidDateTimeFormatException,
            InvalidTimePeriodException {
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
     * @return Task in a displayed string format
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
     * @throws InvalidFormatInStorageException If there is an invalid format in storage.
     */
    public static Task createTaskFromStoredString(String fullDescription) throws InvalidFormatInStorageException {
        String trimmedFullDescription = fullDescription.trim();

        int taskTypeStartPos = 1;
        String taskType = trimmedFullDescription.substring(taskTypeStartPos, taskTypeStartPos + 1);
        TaskIconTypeEnum taskIconTypeEnum = TaskIconTypeEnum.getEnum(taskType);
        if (taskIconTypeEnum == null) {
            String expectedFormat = "One of these task icons " + Arrays.toString(TaskIconTypeEnum.values());
            throw new InvalidFormatInStorageException(fullDescription, expectedFormat);
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
     * Formats the task to storage string form.
     *
     * @return Task in storage string format.
     */
    public abstract String toStorageString();

    /**
     * Checks if the task description contains a keyword.
     * The search is case insensitive.
     *
     * @param keyword Keyword to search for.
     * @return True if task description contains the keyword, false otherwise.
     */
    protected boolean contains(String keyword) {
        return this.description.toLowerCase().contains(keyword.toLowerCase());
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
