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

public class AddEvent extends Command {

    private static final String CMD_FORMAT = "(?i)event.*";
    private static final String CMD_VALID_FORMAT = "(?i)(event ).*\\S+.*( /from )\\d{4}\\s\\d{2}\\s\\d{2}\\s\\d{4}"
            + "( to )\\d{4}\\s\\d{2}\\s\\d{2}\\s\\d{4}";

    public AddEvent(String cmdContent) {
        super(cmdContent);
    }

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

        LocalDateTime from = Parser.parseDateTime(dateTimeArr[0]);
        LocalDateTime to = Parser.parseDateTime(dateTimeArr[1]);
        Event newEvent = new Event(strArr[0].trim(), from, to);

        storage.storeAdd(newEvent.toStorage());
        String message = taskList.addTask(newEvent);
        return ui.showMessage(message);
    }
}
