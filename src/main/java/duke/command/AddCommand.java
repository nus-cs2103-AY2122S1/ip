package duke.command;

import duke.main.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

/**
 * The AddCommand class represents the commands that are related to adding a Task to the TaskList.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Class constructor which receives a Task which is to be added to the list.
     *
     * @param task The Task to be added to the list.
     */
    public AddCommand(Task task) {
        super();
        this.task = task;
    }

    /**
     * Executes the add command, which adds a Task to the list.
     * @param tasks The list of Task.
     * @param ui The Ui objects that handles input from user and output to user.
     * @param storage The Storage object that handles reading/writing of data.
     * @return String The message to display once add command has been completed.
     * @throws DukeException Unused.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(this.task);
        return ui.displayAddedMessage(this.task, tasks);
    }
}
