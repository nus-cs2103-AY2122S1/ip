package duke.commands;

import duke.DukeException;
import duke.TaskList;
import duke.storage.Storage;

public class UndoCommand extends Command {
    private Storage storage;

    public UndoCommand() {}

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    @Override
    public String execute(TaskList prevTasks) throws DukeException {
//        storage.customCopyToFile(prevTasks);
//        storage.readFile();
        storage.replaceTasks(prevTasks);
        return "You've gone back in time.";
    }
}
