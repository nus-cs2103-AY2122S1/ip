package poseidon.command;

import java.util.regex.Pattern;

import poseidon.exception.PoseidonException;
import poseidon.exception.PoseidonIncorrectCommandFormatException;
import poseidon.exception.PoseidonNonExistentTaskException;
import poseidon.parser.Parser;
import poseidon.storage.Storage;
import poseidon.tasklist.TaskList;
import poseidon.ui.Ui;

/**
 * Represents an {@code Delete} object that contains all the functionality of a command for deleting a {@code Task}
 * from the {@code TaskList}.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class Delete extends Command {

    public static final String CMD_USER_FORMAT = "delete 'index'";

    // Private constants dictating format of the command represented by this class.
    private static final String CMD_FORMAT = "(?i)delete.*";
    private static final String CMD_VALID_FORMAT = "(?i)delete\\s+\\d+\\s*";

    /**
     * Constructs a new {@code Delete} object with the given {@code String}.
     *
     * @param cmdContent {@code String} that contains the content of the command.
     */
    public Delete(String cmdContent) {
        super(cmdContent);
    }

    /**
     * Returns whether the given {@code String} is in the format of the command represented by the class
     * {@code Delete} or not.
     *
     * @param cmdContent {@code String} that contains the content of the command to be checked.
     * @return {@code Boolean} - true if the {@code String} matches the command format of this class, else false.
     */
    public static boolean isThisCmd(String cmdContent) {
        return Pattern.compile(CMD_FORMAT).matcher(cmdContent).matches();
    }

    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws PoseidonException {
        if (!Pattern.compile(CMD_VALID_FORMAT).matcher(cmdContent).matches()) {
            throw new PoseidonIncorrectCommandFormatException("DELETE", CMD_USER_FORMAT);
        }

        String indexString = cmdContent.substring(6).trim();
        int indexInt = Parser.parseIndex(indexString);

        if (!taskList.isIndexValid(indexInt)) {
            throw new PoseidonNonExistentTaskException();
        }

        String message = taskList.deleteTask(indexInt);
        storage.storeDelete(indexInt);
        return ui.showMessage(message);
    }
}

