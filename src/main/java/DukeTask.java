/**
 * Encapsulates a task containing a description and status.
 */
public class DukeTask {
    private String description;
    private boolean isDone;

    /**
     * Constructor to instantiate a `DukeTask`.
     * The isDone field is set to false by default as new tasks should not be done yet.
     *
     * @param description describes what the task is
     */
    public DukeTask(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a specific `DukeTask` based on the prefix in the user input.
     * Supported types of tasks include todo, deadline and event.
     *
     * @param description the full user input that may or may not be a valid type of task
     * @return A new `DukeTask`
     * @throws InvalidTaskTypeException when the type of task is not recognised
     * @throws InvalidTaskTimeFormatException when a task does not have valid time inputs
     */
    public static DukeTask createTask(String description, DukeActionTypeEnum actionType)
            throws InvalidTaskTypeException, InvalidTaskTimeFormatException {
        // A valid task is either a to-do, deadline or event

        if (actionType.equals(DukeActionTypeEnum.TODO)) {
            return DukeTodoTask.createTask(description);
        }

        if (actionType.equals(DukeActionTypeEnum.DEADLINE)) {
            return DukeDeadlineTask.createTask(description);
        }

        if (actionType.equals(DukeActionTypeEnum.EVENT)) {
            return DukeEventTask.createTask(description);
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
        return (this.isDone ? "X" : " ");
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
}
