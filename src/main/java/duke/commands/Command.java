package duke.commands;

import duke.Storage;
import duke.Tasklist;
import duke.Ui;
import duke.exceptions.NoSuchTaskException;

import java.io.IOException;

public abstract class Command {

    public abstract void execute(Tasklist taskList, Ui ui, Storage storage) throws NoSuchTaskException, IOException;

    public boolean isExit() {
        return false;
    }
}