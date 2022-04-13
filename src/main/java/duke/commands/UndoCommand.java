package duke.commands;

import duke.DukeException;
import duke.TaskList;
import duke.storage.Storage;
import duke.ui.TextUi;

public class UndoCommand extends Command {
    private Storage storage;

    public UndoCommand() {}

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        this.storage.setTasks(tasks);
        this.storage.copyToFile();
        String response = "You've gone back in time!\n";
        response += TextUi.showTaskList(tasks.getTaskList());
        return response;
    }
}
