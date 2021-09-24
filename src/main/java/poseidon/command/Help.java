package poseidon.command;

import java.util.regex.Pattern;

import poseidon.storage.Storage;
import poseidon.tasklist.TaskList;
import poseidon.ui.Ui;

public class Help extends Command {

    private static final String CMD_FORMAT = "(?i)help\\s*";
    private static final String CMD_VALID_FORMAT = "(?i)help\\s*";

    public Help(String cmdContent) {
        super(cmdContent);
    }

    public static boolean isThisCmd(String cmdContent) {
        return Pattern.compile(CMD_FORMAT).matcher(cmdContent).matches();
    }

    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) {
        return ui.showHelp();
    }
}
