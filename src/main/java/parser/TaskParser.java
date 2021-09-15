package parser;

import exceptions.MorganException;
import tasks.DeadlineTask;
import tasks.EventTask;
import tasks.FixedDurationTask;
import tasks.Task;
import tasks.ToDoTask;

/**
 * This is a parser.TaskParser class, which translates a task string
 * into a Task object and vise versa.
 */
public class TaskParser {
    public static final String DELIMITER = "¬";
    private static final int TASK_INDEX = 0; // Task type
    private static final int STATUS_INDEX = 1; // Task status
    private static final int DESCRIPTION_INDEX = 2; // Task description
    private static final int DATETIME_INDEX = 3; // Task date/time (if applicable)
    private static final String TAMPERED_ERROR = "Storage data has been tampered.";

    /**
     * Translates a task into its storage string representation.
     * @param task The task to be parsed.
     * @return The storage string representation of the task.
     */
    public String encode(Task task) {
        assert task != null;
        String status = String.valueOf(task.getStatus());
        String description = task.getDescription();

        if (task instanceof FixedDurationTask) {
            FixedDurationTask fixed = (FixedDurationTask) task;
            return FixedDurationTask.KEYWORD + DELIMITER + status + DELIMITER
                    + description + DELIMITER + fixed.getDuration();
        }

        if (task instanceof EventTask) {
            EventTask event = (EventTask) task;
            return EventTask.KEYWORD + DELIMITER + status + DELIMITER
                    + description + DELIMITER + event.getDateTime();
        }

        if (task instanceof DeadlineTask) {
            DeadlineTask deadline = (DeadlineTask) task;
            return DeadlineTask.KEYWORD + DELIMITER + status + DELIMITER
                    + description + DELIMITER + deadline.getDateTime();
        }

        if (task instanceof ToDoTask) {
            return ToDoTask.KEYWORD + DELIMITER + status + DELIMITER
                    + description;
        }
        return "";
    }

    /**
     * Translates a task in string format to a Task object.
     * @param taskText The task text to be parsed.
     * @return The Task object based on the string.
     * @throws MorganException If storage data has been tampered.
     */
    public Task decode(String taskText) throws MorganException {
        assert taskText != null;
        String[] data = taskText.split(DELIMITER);

        if (data.length <= DESCRIPTION_INDEX) {
            throw new MorganException(TAMPERED_ERROR);
        }

        String taskType = data[TASK_INDEX].trim();
        boolean isDone = Boolean.parseBoolean(data[STATUS_INDEX].trim());
        String description = data[DESCRIPTION_INDEX].trim();
        String dateTimeString = "";

        Task task;
        switch(taskType) {
        case (FixedDurationTask.KEYWORD):
            if (data.length <= DATETIME_INDEX) {
                throw new MorganException(TAMPERED_ERROR);
            }
            String duration = data[DATETIME_INDEX].trim();
            task = new FixedDurationTask(description, duration);
            break;
        case (EventTask.KEYWORD):
            if (data.length <= DATETIME_INDEX) {
                throw new MorganException(TAMPERED_ERROR);
            }
            dateTimeString = data[DATETIME_INDEX].trim();
            task = new EventTask(description, dateTimeString);
            break;
        case (DeadlineTask.KEYWORD):
            if (data.length <= DATETIME_INDEX) {
                throw new MorganException(TAMPERED_ERROR);
            }
            dateTimeString = data[DATETIME_INDEX].trim();
            task = new DeadlineTask(description, dateTimeString);
            break;
        case (ToDoTask.KEYWORD):
            task = new ToDoTask(description);
            break;
        default:
            throw new MorganException(TAMPERED_ERROR);
        }

        if (isDone) {
            task.markDone();
        }
        return task;
    }
}
