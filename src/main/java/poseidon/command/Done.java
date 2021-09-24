package poseidon.command;

import java.io.IOException;
import java.util.regex.Pattern;

import poseidon.exception.PoseidonException;
import poseidon.parser.Parser;
import poseidon.storage.Storage;
import poseidon.tasklist.TaskList;
import poseidon.ui.Ui;

public class Done extends Command {

    private static final String CMD_FORMAT = "(?i)done\\s+\\d+\\s*";
    private static final String CMD_VALID_FORMAT = "(?i)done\\s+\\d+\\s*";

    public Done(String cmdContent) {
        super(cmdContent);
    }

    public static boolean isThisCmd(String cmdContent) {
        return Pattern.compile(CMD_FORMAT).matcher(cmdContent).matches();
    }

    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws IOException {
        String indexString = cmdContent.substring(4).trim();
        int indexInt = Parser.parseIndex(indexString);

        if (!taskList.isIndexValid(indexInt)) {
            throw new PoseidonException(Ui.NON_EXISTENT_TASK_MSG);
        }

        String message = taskList.markTaskDone(indexInt);
        String taskStorage = taskList.getTaskStorage(indexInt);
        storage.storeDone(indexInt, taskStorage);
        return ui.showMessage(message);
    }
}
