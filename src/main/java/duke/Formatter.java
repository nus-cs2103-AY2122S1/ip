package duke;

import duke.task.Task;
import duke.task.TaskType;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;

public class Formatter {

    public Task formatStringToTask(String input) throws DukeException {
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
