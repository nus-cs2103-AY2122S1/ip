package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.*;

public class AddCommand extends Command {
    Task task;

    /**
     * Constructs an AddCommand object.
     *
     * @param command The type of tasks to be added.
     * @param description The description to be added when creating a task.
     * @throws DukeException If description given is invalid.
     */
    public AddCommand(String command, String description) throws DukeException{
        if (command.equals("todo")) {
            String[] splitString = description.split("todo ");

            if (splitString.length > 1) {
                String taskDescription = splitString[1];
                task = new Todo(taskDescription);
            } else {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }

        } else if (command.equals("deadline")) {
            String[] splitString = description.split("deadline |/by");

            if (splitString.length > 2) {
                String taskDescription = splitString[1];
                String by = splitString[2];
                task = new Deadline(taskDescription, by);
            } else {
                throw new DukeException("OOPS!!! The description/by of a deadline cannot be empty.");
            }

        } else {
            String[] splitString = description.split("event |/at");

            if (splitString.length > 2) {
                String taskDescription = splitString[1];
                String at = splitString[2];
                task = new Event(taskDescription, at);
            } else {
                throw new DukeException("OOPS!!! The description/at of an event cannot be empty.");
            }
        }
    }

    /**
     * Adds and store new task in the list and storage.
     * Sends added message to user.
     *
     * @param tasks Task List that store all current task.
     * @param ui Ui object to interact with user.
     * @param storage Storage that store tasks in hard-drive.
     * @throws DukeException If arguments enters has error.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(task);
        storage.save(tasks);
        ui.showAddTask(task, tasks);
    }

    /**
     * Checks whether the command is an exit command.
     *
     * @return Boolean whether command is an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
