package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

/**
 * Task class that encapsulate task behaviour and data.
 */
public class Task {

    /** Category of task. */
    public enum Type {
        TODO, // task without a date
        EVENT, // task with a start date and end date
        DEADLINE, // task with a single date associated with it.
    }

    private static final String DELIMITER = "--|--";
    private final String title;
    private final Type type;
    private boolean isComplete = false;

    /**
     * Static factory method for creating Tasks.
     *
     * @param inputString Includes title and date string (if applicable).
     * @param type        Task Type (enum)
     * @return instance of a subclass of Task.
     */
    public static Task createTask(String inputString, Type type) {
        inputString = inputString.trim();
        String[] args;
        switch (type) {
        case TODO:
            return new TodoTask(inputString);
        case EVENT:
            args = inputString.split(" /at ");
            if (args.length != 2) {
                throw new InvalidTaskException("Expected '{title} /at {date}' for event tasks");
            }
            LocalDateTime startDate;
            LocalDateTime endDate;
            try {
                String[] arr = args[1].split(" - ");
                if (arr.length != 2) {
                    DateParser.parseDateTimeInput(""); // throws error
                }
                startDate = DateParser.parseDateTimeInput(arr[0]);
                endDate = DateParser.parseDateTimeInput(arr[1]);
            } catch (DateTimeParseException e) {
                throw new InvalidTaskException(
                        "The two dates for deadline creation could not be parsed. Expected:\n"
                                + "2 dates separated by ' - '. Dates come in the forms: "
                                + "'YYYY-MM-DD' or 'YYYY-MM-DD HHMM' (Time in 24hr format)."
                );
            }
            return new EventTask(args[0].trim(), startDate, endDate);
        case DEADLINE:
            args = inputString.split(" /by ");
            if (args.length != 2) {
                throw new InvalidTaskException("Expected '{title} /by {dates}' for deadline tasks");
            }
            LocalDateTime date;
            try {
                date = DateParser.parseDateTimeInput(args[1].trim());
            } catch (DateTimeParseException e) {
                throw new InvalidTaskException(
                        "Date for event creation could not be parsed. Expected:\n"
                                + "'YYYY-MM-DD' or 'YYYY-MM-DD HHMM' (Time in 24hr format)."
                );
            }
            return new DeadlineTask(args[0].trim(), date);
        default:
            throw new InvalidTaskException("Task type not expected.");
        }
    }

    protected Task(String title, Type type) {
        title = title.trim();
        if (title.length() == 0) {
            throw new InvalidTaskException("Task description cannot be empty");
        }
        this.title = title;
        this.type = type;
    }

    /**
     * Converts string (as stored in database) to a task.
     * Throws an error if there is an issue parsing the string.
     *
     * @param stringifiedTask task in string form read from last save.
     * @return Task object
     */
    public static Task stringToTask(String stringifiedTask) {
        // {TYPE}|{DESCRIPTION}|{DATE or DATES or BLANK}
        String[] taskAttr = stringifiedTask.split(Pattern.quote(DELIMITER));
        if (taskAttr.length < 3 || taskAttr.length > 4) {
            throw new IllegalArgumentException(
                    "This task is not correctly stringified. - " + stringifiedTask
            );
        }

        boolean isComplete = Boolean.parseBoolean(taskAttr[0]);
        String type = taskAttr[1];
        String descr = taskAttr[2];
        String date = taskAttr.length == 3 ? "" : taskAttr[3];

        Task task;

        switch (type) {
        case "T":
            task = Task.createTask(descr, Task.Type.TODO);
            break;
        case "E":
            task = Task.createTask(String.format("%s /at %s", descr, date), Task.Type.EVENT);
            break;
        case "D":
            task = Task.createTask(String.format("%s /by %s", descr, date), Task.Type.DEADLINE);
            break;
        default:
            throw new IllegalArgumentException(
                    "This task is not correctly stringified. - " + stringifiedTask
            );
        }

        task.markComplete(isComplete);
        return task;
    }

    /**
     * Converts task into a database ready String representation.
     *
     * @return String representation of the task to be saved in database.
     */
    public String taskToString() {
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
            throw new IllegalArgumentException("Task type enums inconsistently applied");
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
    public void markComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    @Override
    public String toString() {
        return (isComplete ? "[x] " : "[ ] ") + this.title;
    }
}
