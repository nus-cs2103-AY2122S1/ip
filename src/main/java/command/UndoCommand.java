package command;

import duke.Storage;
import duke.Ui;
import task.TaskList;

public class UndoCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks);
        return "undo";
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
