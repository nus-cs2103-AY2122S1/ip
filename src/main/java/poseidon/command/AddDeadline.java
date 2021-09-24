package poseidon.command;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

import poseidon.exception.PoseidonException;
import poseidon.parser.Parser;
import poseidon.storage.Storage;
import poseidon.task.Deadline;
import poseidon.tasklist.TaskList;
import poseidon.ui.Ui;

/**
 * Represents an {@code AddDeadline} object that contains all the functionality of a command for adding a
 * {@code Deadline} task to the {@code TaskList}.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class AddDeadline extends Command {

    // Private constants dictating format of the command represented by this class.
    private static final String CMD_FORMAT = "(?i)deadline.*";
    private static final String CMD_VALID_FORMAT = "(?i)(deadline ).*\\S+.*( /by )\\d{4}\\s\\d{2}\\s\\d{2}\\s\\d{4}";

    /**
     * Constructs a new {@code AddDeadline} object with the given {@code String}.
     *
     * @param cmdContent {@code String} that contains the content of the command.
     */
    public AddDeadline(String cmdContent) {
        super(cmdContent);
    }

    /**
     * Returns whether the given {@code String} is in the format of the command represented by the class
     * {@code AddDeadline} or not.
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
            throw new PoseidonException("There appears to be a typo in your DEADLINE command.\n"
                    + "The command should be of the form:\n"
                    + "  deadline 'description' /by 'yyyy mm dd hhmm'\n"
                    + "Please try again.");
        }

        String[] strArr = cmdContent.substring(8).split(" /by ", 2);
        LocalDateTime byDateTime = Parser.parseDateTime(strArr[1]);
        Deadline newDeadline = new Deadline(strArr[0].trim(), byDateTime);

        storage.storeAdd(newDeadline.toStorage());
        String message = taskList.addTask(newDeadline);
        return ui.showMessage(message);
    }
}
