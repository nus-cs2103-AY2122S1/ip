package duke.command;

import duke.exception.InvalidInputException;
import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class AddCommand extends Command {

    private Task task;

    /**
     * Default constructor for an AddCommand.
     *
     * @param task The task to add.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the specified command.
     *
     * @param tasks The TaskList which we are modifying.
     * @param ui The Ui we will use for user interaction.
     * @param storage The Storage we will use for storing save data.
     * @throws InvalidInputException When the input is deemed invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException {
        tasks.add(task);
        ui.showAdd(task.toString(), tasks.size());
        Command listCommand = new ListCommand();
        listCommand.execute(tasks, ui, storage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
