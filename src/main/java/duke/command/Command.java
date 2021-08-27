package duke.command;

import duke.data.TaskHandler;
import duke.storage.Storage;

public abstract class Command {
    protected TaskHandler taskHandler;
    protected Storage storage;

    public Command(TaskHandler th, Storage str) {
        this.taskHandler = th;
        this.storage = str;
    }

    public abstract void execute(String cmd);

}
