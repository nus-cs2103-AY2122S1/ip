package duke.commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class ByeCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveTasks(tasks);
        ui.showBye();
        ui.close();
    }

    @Override
    public boolean isExit() { 
        return true; 
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof ByeCommand);
    }
}
