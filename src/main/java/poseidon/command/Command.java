package poseidon.command;

import java.io.IOException;

import poseidon.storage.Storage;
import poseidon.tasklist.TaskList;
import poseidon.ui.Ui;

public abstract class Command {

    protected final String cmdContent;

    protected Command(String cmdContent) {
        this.cmdContent = cmdContent;
    }

    public static boolean isThisCmd(String cmdContent) {
        return false;
    }

    public abstract String execute(Storage storage, TaskList taskList, Ui ui) throws IOException;
}
