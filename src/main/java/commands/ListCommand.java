package commands;

import task.TaskList;
import ui.Ui;
import duke.DukeException;
import storage.Storage;

public class ListCommand extends Command {
    public ListCommand() {}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printOutput(tasks.toString());
    }
    
}
