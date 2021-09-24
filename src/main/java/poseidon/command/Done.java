package poseidon.command;

import java.io.IOException;
import java.util.regex.Pattern;

import poseidon.exception.PoseidonException;
import poseidon.parser.Parser;
import poseidon.storage.Storage;
import poseidon.tasklist.TaskList;
import poseidon.ui.Ui;

/**
 * Represents an {@code Done} object that contains all the functionality of a command for marking a {@code Task} as
 * done in the {@code TaskList}.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class Done extends Command {

    // Private constants dictating format of the command represented by this class.
    private static final String CMD_FORMAT = "(?i)done.*";
    private static final String CMD_VALID_FORMAT = "(?i)done\\s+\\d+\\s*";

    /**
     * Constructs a new {@code Done} object with the given {@code String}.
     *
     * @param cmdContent {@code String} that contains the content of the command.
     */
    public Done(String cmdContent) {
        super(cmdContent);
    }

    /**
     * Returns whether the given {@code String} is in the format of the command represented by the class
     * {@code Done} or not.
     *
     * @param cmdContent {@code String} that contains the content of the command to be checked.
     * @return {@code Boolean} - true if the {@code String} matches the command format of this class, else false.
     */
    public static boolean isThisCmd(String cmdContent) {
        return Pattern.compile(CMD_FORMAT).matcher(cmdContent).matches();
    }

    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws IOException {
        if (!Pattern.compile(CMD_VALID_FORMAT).matcher(cmdContent).matches()) {
            throw new PoseidonException("There appears to be a typo in your DONE command.\n"
                    + "The command should be of the form:\n"
                    + "  done 'index'\n"
                    + "Please try again.");
        }

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
