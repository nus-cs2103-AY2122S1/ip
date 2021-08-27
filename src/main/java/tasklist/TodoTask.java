package tasklist;

public class TodoTask extends Task {
    private TodoTask(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Processes the input string to create a todo task.
     *
     * @param description Input task string.
     * @return App representation of a task containing an action description.
     */
    public static TodoTask createTask(String description) {
        return new TodoTask(description, false);
    }

    /**
     * Formats the task in string form, displaying the task type, status and description.
     *
     * @return Task in a displayed string format.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Creates an app representation of a todo task from the storage representation of the task.
     *
     * @param description Storage representation of a todo task.
     * @return App representation of a todo task.
     */
    public static TodoTask createTaskFromStoredString(String description) {
        String statusIcon = description.substring(1, 2);
        boolean isDone = false;
        if (statusIcon.equals(STATUS_ICON_DONE)) {
            isDone = true;
        }

        String actionDescription = description.substring(3).trim();

        return new TodoTask(actionDescription, isDone);
    }
}
