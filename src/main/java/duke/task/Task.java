package duke.task;

/**
 * Task is the base class for all tasks stored in the Duke application.
 */
public class Task {
    protected static final String STORAGE_STRING_PARSING_DELIMITER = " \\| ";
    protected static final String STORAGE_STRING_DELIMITER = " | ";

    private static final String NOT_DONE_STATUS_ICON = " ";
    private static final String DONE_STATUS_ICON = "X";
    private static final String DONE_CHARACTER = "1";
    private static final String NOT_DONE_CHARACTER = "0";

    protected String description;
    protected boolean isDone;

    /**
     * Creates a task with the specified description. The task defaults to is not done.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks a task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Checks whether the Task description matches a given String.
     * @param regex The regular expression String to match.
     * @return Boolean representing whether Task matches String.
     */
    public boolean descriptionMatches(String regex) {
        return this.description.matches(String.format("(.*)%s(.*)", regex));
    }

    /**
     * Gets the String icon that represents the status of the task.
     * @return The String icon.
     */
    public String getStatusIcon() {
        return this.isDone ? Task.DONE_STATUS_ICON : Task.NOT_DONE_STATUS_ICON;
    }

    /**
     * Converts the Task into a String that represents the Task.
     * @return The String representation of a Task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    /**
     * Converts the Task into a String to be stored in Storage.
     * @return String to be stored
     */
    public String toStorageString() {
        String isDoneString = this.isDone ? DONE_CHARACTER : NOT_DONE_CHARACTER;
        return isDoneString + Task.STORAGE_STRING_DELIMITER + this.description;
    }

    /**
     * Parses a storage String into a Task object.
     * @param string The String used to represent the Task in the Storage.
     * @return The Task represented by the Storage String.
     */
    public static Task fromStorageString(String string) {
        String[] taskSubstrings = string.split(Task.STORAGE_STRING_PARSING_DELIMITER);
        TaskType taskType = TaskType.parse(taskSubstrings[0]);

        Task task;
        switch (taskType) {
        case DEADLINE:
            task = new Deadline(taskSubstrings[2], taskSubstrings[3]);
            break;
        case EVENT:
            task = new Event(taskSubstrings[2], taskSubstrings[3]);
            break;
        case TODO:
            task = new ToDo(taskSubstrings[2]);
            break;
        default:
            throw new RuntimeException(String.format(
                "duke.task.TaskType %s is not accounted for in switch statement.",
                taskType.toString()
            ));
        }

        if (taskSubstrings[1].equals(Task.DONE_CHARACTER)) {
            task.isDone = true;
        }

        return task;
    }
}
