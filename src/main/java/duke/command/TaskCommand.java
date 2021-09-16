package duke.command;

import static duke.utils.Utils.splitBetween;

import duke.exception.DukeException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.exception.InvalidCommandException;
import duke.exception.TaskFormatEmptyException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class TaskCommand extends Command {

    public TaskCommand(String userCommand, String userArgument) {
        super(userCommand, userArgument);
    }

    public String execute(TaskList tasks, Storage storage) throws DukeException {

        assert userArgument != null;

        if (userArgument.equals("")) {
            throw new TaskFormatEmptyException();
        }

        switch (userCommand) {
        case Command.TODO_COMMAND:
            tasks.addTask(new Todo(userArgument, false));
            break;
        case Command.DEADLINE_COMMAND:
            String[] deadlineInfo = splitBetween(userArgument, "/by");
            tasks.addTask(new Deadline(deadlineInfo[0], deadlineInfo[1], false));
            break;
        case Command.EVENT_COMMAND:
            String[] eventInfo = splitBetween(userArgument, "/at");
            tasks.addTask(new Event(eventInfo[0], eventInfo[1], false));
            break;
        default:
            throw new InvalidCommandException();
        }
        return addTask(tasks.getTask(tasks.numberOfTasks() - 1), storage, tasks);
    }

    private static String addTask(Task newTask, Storage storage, TaskList tasks) throws DukeException {
        storage.saveTaskToFile(newTask);
        String addedTask = String.format("Got it, I've added this task:\n %s\n", newTask.toString());
        String numberOfTasks = String.format("Now you have %d tasks in your list.\n", tasks.numberOfTasks());
        return addedTask + numberOfTasks;
    }
}
