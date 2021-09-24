package poseidon.command;

import java.io.IOException;
import java.util.regex.Pattern;

import poseidon.storage.Storage;
import poseidon.tasklist.TaskList;
import poseidon.ui.Ui;

public class Sort extends Command {

    private static final String CMD_FORMAT = "(?i)list\\s*-s\\s*";
    private static final String CMD_VALID_FORMAT = "(?i)list\\s*-s\\s*";

    public Sort(String cmdContent) {
        super(cmdContent);
    }

    public static boolean isThisCmd(String cmdContent) {
        return Pattern.compile(CMD_FORMAT).matcher(cmdContent).matches();
    }

    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws IOException {
        return ui.getListMessage(taskList.sortTasks());
    }
}
