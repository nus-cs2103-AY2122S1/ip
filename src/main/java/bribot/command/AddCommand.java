package bribot.command;

import bribot.exception.DukeException;
import bribot.storage.Storage;
import bribot.task.Task;
import bribot.task.TaskList;
import bribot.ui.Ui;

/**
 * Represents an add command where it is given a task to add to the tasklist
 */

public class AddCommand extends Command {
    private Task task;

    /**
     * Constructor to create an AddCommand with the task that it will add.
     * @param task
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Returns true if the command is an exit command.
     * @return false since the add command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the add command. Where the task is added to the tasklist and the ui prints out a
     * confirmation that the command was added.
     * @param tasks the TaskList of the program.
     * @param ui the ui of the program.
     * @param storage the storage of the program.
     * @throws DukeException thrown if there is error when trying to add command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(task);
        storage.save(tasks.getTasks());
        return ui.printAddedTask(task);
    }

    /**
     * Returns a boolean which is true when another object is the same by comparing their tasks.
     * @param obj the other AddCommand
     * @return a boolean that is true if the AddCommands are the same.
     */
    @Override
    public boolean equals(Object obj) {
        AddCommand other = (AddCommand) obj;
        return this.task.toString().equals(other.task.toString());
    }
}
