package duke.tasks;

import java.time.LocalDateTime;

import duke.exceptions.AuguryException;
import duke.exceptions.InvalidTaskCreationException;
import duke.util.StringCleaner;

/**
 * The {@code TaskFactory} is a factory class responsible for creating {@code Task}s.
 */
public class TaskFactory {

    public TaskFactory() { }

    /**
     * Creates a single {@code Task} from the given {@code String newTaskDetails}.
     * This method signature is called when creating tasks from the save file.
     *
     * @param newTaskDetails {@code String} containing details of the task.
     *                                     Example: {@code [E] event (at: 6 Sep 2021, Tue, 21:00)}
     */
    public Task createTask(String newTaskDetails) {
        // this method is used when reading from duke.tasks.txt file
        // the syntax is [E][X] description (at: time)

        if (newTaskDetails.startsWith("[T")) {
            boolean isDone = newTaskDetails.charAt(4) == 'X';
            String description = newTaskDetails.split("] ")[1].trim();
            return new TodoTask(description, isDone);
        } else if (newTaskDetails.startsWith("[E")) {
            boolean isDone = newTaskDetails.charAt(4) == 'X';
            String description = newTaskDetails.split("] ")[1]
                    .split(" \\(")[0]
                    .trim();
            String timeString = newTaskDetails.split("\\(at: ")[1]
                    .replaceAll(".$", ""); // get rid of last character ')'
            LocalDateTime time = LocalDateTime.parse(timeString, Task.TIME_DISPLAY_FORMAT);
            return new EventTask(description, time, isDone);
        } else if (newTaskDetails.startsWith("[D")) {
            boolean isDone = newTaskDetails.substring(4).equals("X");
            String description = newTaskDetails.split("] ")[1]
                    .split(" \\(")[0]
                    .trim();
            String timeString = newTaskDetails.split("\\(by: ")[1]
                    .replaceAll(".$", ""); // get rid of last character ')'
            LocalDateTime time = LocalDateTime.parse(timeString, Task.TIME_DISPLAY_FORMAT);
            return new DeadlineTask(description, time, isDone);
        } else {
            return null;
        }
    }

    /**
     * Creates a single {@code Task} from the given {@code String newTaskType} and {@code String newTaskDetails}.
     * This method signature is called when creating tasks from user input.
     *
     * @param newTaskType {@code String}, one of: 'todo', 'event', 'deadline'.
     * @param newTaskDetails {@code String} representing full user input.
     *                                     Example: {@code event shopping /at 2021-08-08 2300}
     * @throws InvalidTaskCreationException If user input has missing paramenters or malformed date.
     */
    public Task createTask(String newTaskType, String newTaskDetails) throws AuguryException {
        if (newTaskType == null) {
            return null;
        } else if (newTaskType.equalsIgnoreCase(Task.TaskTypes.TODO.toString())) {
            checkDetailsNonEmpty(newTaskType, newTaskDetails);
            String description = newTaskDetails.substring(5).trim();
            return new TodoTask(description);
        } else if (newTaskType.equalsIgnoreCase(Task.TaskTypes.EVENT.toString())) {
            checkDetailsNonEmpty(newTaskType, newTaskDetails);
            checkTaskIncludesTime(newTaskType, newTaskDetails);
            String description = newTaskDetails.substring(6).split("/at ")[0].trim();
            String timeString = newTaskDetails.split("/at ")[1].trim();
            LocalDateTime time = StringCleaner.toLocalDateTime(timeString);
            return new EventTask(description, time);
        } else if (newTaskType.equalsIgnoreCase(Task.TaskTypes.DEADLINE.toString())) {
            checkDetailsNonEmpty(newTaskType, newTaskDetails);
            checkTaskIncludesTime(newTaskType, newTaskDetails);
            String description = newTaskDetails.substring(9).split("/by ")[0].trim();
            String timeString = newTaskDetails.split("/by ")[1].trim();
            LocalDateTime time = StringCleaner.toLocalDateTime(timeString);
            return new DeadlineTask(description, time);
        } else {
            return null;
        }
    }

    private void checkDetailsNonEmpty(String newTaskType, String details) throws AuguryException {
        int commandLength = newTaskType.length() + 1;
        if (details.length() <= commandLength
                || details.contains("/at") && details.split("/at")[0].length() <= commandLength
                || details.contains("/by") && details.split("/by")[0].length() <= commandLength) {
            throw new InvalidTaskCreationException("Description of " + newTaskType + " cannot be empty!");
        }
    }

    private void checkTaskIncludesTime(String newTaskType, String details) throws AuguryException {
        if (newTaskType.equalsIgnoreCase(Task.TaskTypes.EVENT.toString())) {
            if (details.split("/at ").length < 2 || details.split("/at ")[1].length() <= 1) {
                throw new InvalidTaskCreationException("Event task must include time! (use /at YYYY-MM-DD HHMM)");
            }
        } else if (newTaskType.equalsIgnoreCase(Task.TaskTypes.DEADLINE.toString())) {
            if (details.split("/by ").length < 2 || details.split("/by ")[1].length() <= 1) {
                throw new InvalidTaskCreationException("Deadline task must include time! (use /by YYYY-MM-DD HHMM)");
            }
        }
    }
}
