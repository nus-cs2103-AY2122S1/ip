package duke.command;

import static duke.Utils.splitBetween;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class TaskCommand extends Command {

    public TaskCommand(String userCommand, String userArgument) {
        super(userCommand, userArgument);
    }

    public String execute(TaskList tasks, Storage storage) throws DukeException {
        if (userArgument.equals("")) {
            throw new DukeException("The description of a Task cannot be empty.");
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
            throw new DukeException("Unknown command!");
        }
        return addTask(tasks.getTask(tasks.numberOfTasks() - 1), storage, tasks);
    }

    private static String addTask(Task newTask, Storage storage, TaskList tasks) {
        storage.saveTaskToFile(newTask);
        String addedTask = String.format("Got it, I've added this task:\n %s\n", newTask.toString());
        String numberOfTasks = String.format("Now you have %d tasks in your list.\n", tasks.numberOfTasks());
        return addedTask + numberOfTasks;
    }
}
