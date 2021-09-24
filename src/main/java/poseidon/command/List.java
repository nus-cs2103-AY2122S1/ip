package poseidon.command;

import java.util.regex.Pattern;

import poseidon.storage.Storage;
import poseidon.tasklist.TaskList;
import poseidon.ui.Ui;

/**
 * Represents an {@code List} object that contains all the functionality of a command for listing all the {@code Task}s
 * in the {@code TaskList}.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class List extends Command {

    public static final String CMD_USER_FORMAT = "list";

    // Private constants dictating format of the command represented by this class.
    private static final String CMD_FORMAT = "(?i)list\\s*";

    /**
     * Constructs a new {@code List} object with the given {@code String}.
     *
     * @param cmdContent {@code String} that contains the content of the command.
     */
    public List(String cmdContent) {
        super(cmdContent);
    }

    /**
     * Returns whether the given {@code String} is in the format of the command represented by the class
     * {@code List} or not.
     *
     * @param cmdContent {@code String} that contains the content of the command to be checked.
     * @return {@code Boolean} - true if the {@code String} matches the command format of this class, else false.
     */
    public static boolean isThisCmd(String cmdContent) {
        return Pattern.compile(CMD_FORMAT).matcher(cmdContent).matches();
    }

    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) {
        return ui.getListMessage(taskList.getList());
    }
}
