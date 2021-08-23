package duke.command;

import duke.Storage;
import duke.UI;
import duke.task.TaskList;

import java.io.FileNotFoundException;

public abstract class Command {

    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws FileNotFoundException;

    public boolean isExit() {
        return false;
    }
}
