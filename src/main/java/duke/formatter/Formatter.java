package duke.formatter;

import duke.exception.TimedTaskDateInputException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskType;
import duke.task.Todo;

/**
 * The class that will format a string to a task or a task to a string.
 */
public class Formatter {

    /**
     * Method that formats a String to a Task object.
     * @param input the string from file.
     * @return the formatted task.
     * @throws TimedTaskDateInputException if there are any errors.
     */
    public Task formatStorageStringToTask(String input) throws TimedTaskDateInputException {
        String[] delimitedInput = input.split("[|]");
        String taskType = delimitedInput[0];
        if (taskType.equals(TaskType.T.toString())) {
            return new Todo(delimitedInput[2], Boolean.parseBoolean(delimitedInput[1]));
        }
        if (taskType.equals(TaskType.D.toString())) {
            return new Deadline(delimitedInput[2], delimitedInput[3], Boolean.parseBoolean(delimitedInput[1]));
        }
        if (taskType.equals(TaskType.E.toString())) {
            return new Event(delimitedInput[2], delimitedInput[3], Boolean.parseBoolean(delimitedInput[1]));
        }
        return Task.emptyTask();
    }

    /**
     * Method that formats a Task to a String.
     * @param input the task from a list.
     * @return the formatted string.
     */
    public String formatTaskToString(Task input) {
        StringBuilder result = new StringBuilder();
        TaskType taskType = input.getTaskType();
        result.append(taskType.toString())
                .append("|")
                .append(input.getCompleted())
                .append("|")
                .append(input.getLabel());
        return result.toString();
    }
}
