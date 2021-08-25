package duke.command;

import java.io.IOException;
import duke.TaskList;
import duke.Ui;
import duke.Storage;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;

    public boolean isExit() {
        return false;
    }
    
}