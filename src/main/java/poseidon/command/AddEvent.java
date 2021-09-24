package poseidon.command;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

import poseidon.exception.PoseidonException;
import poseidon.parser.Parser;
import poseidon.storage.Storage;
import poseidon.task.Event;
import poseidon.tasklist.TaskList;
import poseidon.ui.Ui;

/**
 * Represents an {@code AddEvent} object that contains all the functionality of a command for adding a {@code Event}
 * task to the {@code TaskList}.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class AddEvent extends Command {

    // Private constants dictating format of the command represented by this class.
    private static final String CMD_FORMAT = "(?i)event.*";
    private static final String CMD_VALID_FORMAT = "(?i)(event ).*\\S+.*( /from )\\d{4}\\s\\d{2}\\s\\d{2}\\s\\d{4}"
            + "( to )\\d{4}\\s\\d{2}\\s\\d{2}\\s\\d{4}";

    /**
     * Constructs a new {@code AddEvent} object with the given {@code String}.
     *
     * @param cmdContent {@code String} that contains the content of the command.
     */
    public AddEvent(String cmdContent) {
        super(cmdContent);
    }

    /**
     * Returns whether the given {@code String} is in the format of the command represented by the class
     * {@code AddEvent} or not.
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
            throw new PoseidonException("There appears to be a typo in your EVENT command.\n"
                    + "The command should be of the form:\n"
                    + "  event 'description' /from 'yyyy mm dd hhmm' to 'yyyy mm dd hhmm'\n"
                    + "Please try again.");
        }

        String[] strArr = cmdContent.substring(5).split(" /from ", 2);
        String[] dateTimeArr = strArr[1].split(" to ", 2);

        LocalDateTime fromDateTime = Parser.parseDateTime(dateTimeArr[0]);
        LocalDateTime toDateTime = Parser.parseDateTime(dateTimeArr[1]);

        if (fromDateTime.isAfter(toDateTime)) {
            throw new PoseidonException("There appears to be a typo in your EVENT command.\n"
                    + "The event's from/start time is before its to/end time\n"
                    + "Based on our current knowledge of the Arrow of Time, this is impossible.\n"
                    + "Please try again.");
        }

        Event newEvent = new Event(strArr[0].trim(), fromDateTime, toDateTime);

        storage.storeAdd(newEvent.toStorage());
        String message = taskList.addTask(newEvent);
        return ui.showMessage(message);
    }
}
