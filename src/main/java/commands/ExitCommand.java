package commands;

import tasks.TaskList;
import utils.Storage;
import utils.Ui;

public class ExitCommand extends Command{

    public boolean execute(TaskList tasks, Ui ui, Storage storage) {        
        return false;
    }
}
