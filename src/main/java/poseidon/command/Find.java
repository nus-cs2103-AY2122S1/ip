package poseidon.command;

import java.io.IOException;
import java.util.regex.Pattern;

import poseidon.exception.PoseidonException;
import poseidon.storage.Storage;
import poseidon.tasklist.TaskList;
import poseidon.ui.Ui;

/**
 * Represents an {@code Find} object that contains all the functionality of a command for finding {@code Task}s that
 * contain given content.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class Find extends Command {

    // Private constants dictating format of the command represented by this class.
    private static final String CMD_FORMAT = "(?i)find.*";
    private static final String CMD_VALID_FORMAT = "(?i)find\\s+\\S+.*";

    /**
     * Constructs a new {@code Find} object with the given {@code String}.
     *
     * @param cmdContent {@code String} that contains the content of the command.
     */
    public Find(String cmdContent) {
        super(cmdContent);
    }

    /**
     * Returns whether the given {@code String} is in the format of the command represented by the class
     * {@code Find} or not.
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
            throw new PoseidonException("There appears to be a typo in your FIND command.\n"
                    + "The command should be of the form:\n"
                    + "  find 'content'\n"
                    + "Please try again.");
        }

        String findContent = cmdContent.substring(4).trim();
        return ui.showFindList(taskList.findTasks(findContent));
    }
}
