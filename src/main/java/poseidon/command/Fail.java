package poseidon.command;

import java.io.IOException;

import poseidon.storage.Storage;
import poseidon.tasklist.TaskList;
import poseidon.ui.Ui;

public class Fail extends Command {

    public Fail(String cmdContent) {
        super(cmdContent);
    }

    public static boolean isThisCmd(String cmdContent) {
        return true;
    }

    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws IOException {
        return ui.showCommandFail();
    }
}
