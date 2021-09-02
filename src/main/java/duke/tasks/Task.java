package duke.tasks;

import java.util.regex.Pattern;

/**
 * Task class that encapsulate task behaviour and data.
 */
public class Task {

    /**
     * Category of task.
     */
    public enum Type {
        TODO, // task without a date
        EVENT, // task with a start date and end date
        DEADLINE, // task with a single date associated with it.
    }

    private static final String DELIMITER = "--|--";
    private final String title;
    private final Type type;
    private boolean isComplete = false;

    protected Task(String title, Type type) {
        title = title.trim();
        if (title.length() == 0) {
            throw new InvalidTaskException("Task description cannot be empty");
        }
        this.title = title;
        this.type = type;
    }

    /**
     * Static factory method for creating Tasks.
     *
     * @param inputString Includes title and date string (if applicable).
     * @param type        Task Type (enum)
     * @return instance of a subclass of Task.
     */
    public static Task createTask(String inputString, Type type) {
        inputString = inputString.trim();
        switch (type) {
        case TODO:
            return new TodoTask(inputString);
        case EVENT:
            return EventTask.of(inputString);
        case DEADLINE:
            return DeadlineTask.of(inputString);
        default:
            throw new InvalidTaskException("Task type not expected.");
        }
    }

    /**
     * Converts string (as stored in database) to a task.
     * Throws an error if there is an issue parsing the string.
     *
     * @param taskInDatabaseForm task in string form read from last save.
     * @return Task object
     */
    public static Task parseTaskFromDatabase(String taskInDatabaseForm) {
        // {isComplete}|{type}|{description}|{date or dates or blank}
        String[] taskAttributes = taskInDatabaseForm.split(Pattern.quote(DELIMITER));
        if (taskAttributes.length < 3 || taskAttributes.length > 4) {
            throw new IllegalArgumentException(
                "This string could not be parsed into a task. - " + taskInDatabaseForm
            );
        }

        boolean isComplete = Boolean.parseBoolean(taskAttributes[0]);
        String type = taskAttributes[1];
        String description = taskAttributes[2];
        String date = taskAttributes.length == 3 ? "" : taskAttributes[3];

        Task task;

        switch (type) {
        case "T":
            task = Task.createTask(description, Task.Type.TODO);
            break;
        case "E":
            task =
                Task.createTask(
                    String.format("%s /at %s", description, date),
                    Task.Type.EVENT
                );
            break;
        case "D":
            task =
                Task.createTask(
                    String.format("%s /by %s", description, date),
                    Task.Type.DEADLINE
                );
            break;
        default:
            throw new IllegalArgumentException(
                "This string could not be parsed into a task. - " + taskInDatabaseForm
            );
        }

        task.setComplete(isComplete);
        return task;
    }

    /**
     * Converts task into a database ready String representation.
     *
     * @return String representation of the task to be saved in database.
     */
    public String convertToDatabaseFormat() {
        String type;
        switch (this.type) {
        case EVENT:
            type = "E";
            break;
        case DEADLINE:
            type = "D";
            break;
        case TODO:
            type = "T";
            break;
        default:
            throw new IllegalArgumentException(
                "Task type enums inconsistently applied"
            );
        }
        return String.format(
            "%b%s%s%s%s%s",
            this.isComplete,
            DELIMITER,
            type,
            DELIMITER,
            this.title,
            DELIMITER
        );
    }

    /**
     * Set a task to be completed.
     *
     * @param isComplete new completion status
     */
    public void setComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    @Override
    public String toString() {
        return (isComplete ? "[x] " : "[ ] ") + this.title;
    }
}
