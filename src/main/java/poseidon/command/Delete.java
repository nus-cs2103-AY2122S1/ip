package poseidon.command;

import java.io.IOException;
import java.util.regex.Pattern;

import poseidon.exception.PoseidonException;
import poseidon.parser.Parser;
import poseidon.storage.Storage;
import poseidon.tasklist.TaskList;
import poseidon.ui.Ui;

public class Delete extends Command {

    private static final String CMD_FORMAT = "(?i)delete\\s+\\d+\\s*";
    private static final String CMD_VALID_FORMAT = "(?i)delete\\s+\\d+\\s*";

    public Delete(String cmdContent) {
        super(cmdContent);
    }

    public static boolean isThisCmd(String cmdContent) {
        return Pattern.compile(CMD_FORMAT).matcher(cmdContent).matches();
    }

    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws IOException {
        String indexString = cmdContent.substring(6).trim();
        int indexInt = Parser.parseIndex(indexString);

        if (!taskList.isIndexValid(indexInt)) {
            throw new PoseidonException(Ui.NON_EXISTENT_TASK_MSG);
        }

        String message = taskList.deleteTask(indexInt);
        storage.storeDelete(indexInt);
        return ui.showMessage(message);
    }
}

