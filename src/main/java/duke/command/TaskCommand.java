package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.StringJoiner;

public class TaskCommand extends Command {

    public TaskCommand(String userCommand, String userArgument) {
        super(userCommand, userArgument);
    }

    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        if (userArgument.equals("")) {
            throw new DukeException("The description of a duke.task.Task cannot be empty.");
        }

        if (userCommand.equals("todo")) {
            tasks.addTask(new Todo(userArgument, false));
        } else if (userCommand.equals("deadline")) {
            String[] deadlineInfo = splitBetween(userArgument, "/by");
            tasks.addTask(new Deadline(deadlineInfo[0], deadlineInfo[1], false));
        } else {
            String[] eventInfo = splitBetween(userArgument, "/at");
            tasks.addTask(new Event(eventInfo[0], eventInfo[1], false));
        }
        addTask(tasks.getTask(tasks.numberOfTasks() - 1), storage, tasks, ui);
    }

    public boolean isExit() {
        return false;
    }

    private static void addTask(Task newTask, Storage storage, TaskList tasks, UI ui) {
        storage.saveTaskToFile(newTask);
        ui.showMessage(String.format("Got it, I've added this task:\n %s\n", newTask.toString()));
        ui.showMessage(String.format("Now you have %d tasks in your list.\n", tasks.numberOfTasks()));
    }

    private static String[] splitBetween(String str, String separator) throws DukeException {
        StringJoiner start = new StringJoiner(" ");
        StringJoiner end = new StringJoiner(" ");

        int i = 0;
        boolean after = false;

        String[] strArray = str.split(" ");

        while (i < strArray.length) {
            String currentString = strArray[i];
            if (after) {
                end.add(currentString);
            } else if (currentString.equals(separator)) {
                after = true;
            } else {
                start.add(currentString);
            }
            i++;
        }

        if (!after) {
            throw new DukeException("Incorrect format for command.");
        }

        if (String.valueOf(end).equals("")) {
            throw new DukeException("Please specify a time for the task.");
        }

        return new String[]{String.valueOf(start), String.valueOf(end)};
    }

}
