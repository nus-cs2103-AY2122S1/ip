package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * AddCommand add task to task list.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructs an AddCommand object.
     *
     * @param command The type of tasks to be added.
     * @param description The description to be added when creating a task.
     * @throws DukeException If description given is invalid.
     */
    public AddCommand(String command, String description) throws DukeException {
        this.isExit = false;

        switch(command) {
        case "todo":
            task = AddCommand.createTodo(description);
            break;

        case "deadline":
            task = AddCommand.createDeadline(description);
            break;

        case "event":
            task = AddCommand.createEvent(description);
            break;

        default:
            throw new DukeException("The command enter is invalid");
        }

    }

    /**
     * Adds and store new task in the list and storage.
     * Returns added message to be sent to user.
     *
     * @param tasks Task List that store all current task.
     * @param ui Ui object to interact with user.
     * @param storage Storage that store tasks in hard-drive.
     * @return Add message to be sent to user.
     * @throws DukeException If arguments enters has error.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert tasks != null;
        assert ui != null;
        assert storage != null;

        tasks.addTask(task);
        storage.save(tasks);
        return ui.createAddTaskMessage(task, tasks);
    }


    private static Task createTodo(String description) throws DukeException {
        String[] splitString = description.split("todo ");
        Task newTask;

        if (splitString.length > 1) {
            String taskDescription = splitString[1];
            newTask = new Todo(taskDescription);
        } else {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }

        return newTask;
    }

    private static Task createDeadline(String description) throws DukeException {
        String[] splitString = description.split("deadline |/by");
        Task newTask;

        if (splitString.length > 2) {
            String taskDescription = splitString[1];
            String by = splitString[2];
            newTask = new Deadline(taskDescription, by);
        } else {
            throw new DukeException("OOPS!!! The description/by of a deadline cannot be empty.");
        }

        return newTask;
    }

    private static Task createEvent(String description) throws DukeException {
        String[] splitString = description.split("event |/at");
        Task newTask;

        if (splitString.length > 2) {
            String taskDescription = splitString[1];
            String at = splitString[2];
            newTask = new Event(taskDescription, at);
        } else {
            throw new DukeException("OOPS!!! The description/at of an event cannot be empty.");
        }

        return newTask;
    }
}
