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
     * @return
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException {
        String list = "";
        tasks.add(task);
        for (int j = 0; j < tasks.size(); j++) {
            list += (j + 1) + ". " + tasks.get(j).toString() + "\n";
        }
        return ui.showAdd(task.toString(), tasks.size()) + list;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
