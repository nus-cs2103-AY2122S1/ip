package duke.commands;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.DukeException;
import duke.storage.Storage;

public class ListCommand extends Command {
    public ListCommand() {}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printOutput(tasks.toString());
    }
    
}
