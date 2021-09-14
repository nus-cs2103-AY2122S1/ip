package tasklist;

import exception.InvalidFormatInStorageException;
import type.TaskIconTypeEnum;

/**
 * Encapsulates a task with only a description.
 */
public class TodoTask extends Task {
    private TodoTask(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Creates a todo task from a description.
     *
     * @param description Description including action.
     * @return Todo task.
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
        return String.format("[%s]%s",
                TaskIconTypeEnum.TODO.toString(),
                super.toString()
        );
    }

    /**
     * Formats the task to storage string form.
     *
     * @return Task in storage string format.
     */
    @Override
    public String toStorageString() {
        return this.toString();
    }

    /**
     * Creates an app representation of a todo task from the storage representation of the task.
     *
     * @param description Storage representation of a todo task.
     * @return App representation of a todo task.
     * @throws InvalidFormatInStorageException If there is an invalid format in storage.
     */
    public static TodoTask createTaskFromStoredString(String description) throws InvalidFormatInStorageException {
        boolean isDone = Task.isStorageTaskDone(description);

        int actionDescriptionStartPos = 3;
        String actionDescription = description.substring(actionDescriptionStartPos).trim();
        if (actionDescription.isEmpty()) {
            String expectedFormat = "[] <description>";
            throw new InvalidFormatInStorageException(description, expectedFormat);
        }

        return new TodoTask(actionDescription, isDone);
    }

    @Override
    protected boolean isDuplicateOf(Task task) {
        // A different type of task is definitely not a duplicate
        if (!(task instanceof TodoTask)) {
            return false;
        }

        return this.isSameDescription(task);
    }
}
