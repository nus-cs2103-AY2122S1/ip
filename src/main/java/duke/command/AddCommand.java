package duke.command;

import duke.exception.InvalidInputException;
import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class AddCommand extends Command {

    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

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
