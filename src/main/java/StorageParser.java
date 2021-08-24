import tasks.Task;
import tasks.EventTask;
import tasks.DeadlineTask;
import tasks.ToDoTask;
import exceptions.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StorageParser {
    private final static int TASK_INDEX = 0; // Task type
    private final static int STATUS_INDEX = 1; // Task status
    private final static int DESCRIPTION_INDEX = 2; // Task description
    private final static int DATETIME_INDEX = 3; // Task date/time (if applicable)
    protected final static String DELIMITER = "¬";

    public String encode(Task task) {
        StringBuilder output = new StringBuilder();
        String status = String.valueOf(task.getStatus());
        String description = task.getDescription();

        if (task instanceof EventTask) {
            EventTask event = (EventTask) task;
            return EventTask.KEYWORD + DELIMITER + status + DELIMITER
                    + description + DELIMITER + event.getDateTime();
        } else if (task instanceof DeadlineTask) {
            DeadlineTask deadline = (DeadlineTask) task;
            return DeadlineTask.KEYWORD + DELIMITER + status + DELIMITER
                    + description + DELIMITER + deadline.getDateTime();
        } else if (task instanceof ToDoTask) {
            ToDoTask todo = (ToDoTask) task;
            return ToDoTask.KEYWORD + DELIMITER + status + DELIMITER
                    + description;
        }
        return "";
    }

    public Task decode(String string) throws DukeException {
        String[] data = string.split(DELIMITER);
        if (data.length <= DESCRIPTION_INDEX) {
            throw new DukeException("OOPS!!! Storage data has been tampered.");
        }
        String taskType = data[TASK_INDEX].trim();
        boolean isDone = Boolean.parseBoolean(data[STATUS_INDEX].trim());
        String description = data[DESCRIPTION_INDEX].trim();
        String dateTimeString = "";

        Task task;
        switch(taskType) {
            case (EventTask.KEYWORD):
                if (data.length <= DATETIME_INDEX) {
                    throw new DukeException("OOPS!!! Storage data has been tampered.");
                }
                dateTimeString = data[DATETIME_INDEX].trim();
                task = new EventTask(description, dateTimeString);
                break;
            case (DeadlineTask.KEYWORD):
                if (data.length <= DATETIME_INDEX) {
                    throw new DukeException("OOPS!!! Storage data has been tampered.");
                }
                dateTimeString = data[DATETIME_INDEX].trim();
                task = new EventTask(description, dateTimeString);
                break;
            default:
                task = new ToDoTask(description);
        }
        if (isDone) {
            task.markDone();
        }
        return task;
    }
}



