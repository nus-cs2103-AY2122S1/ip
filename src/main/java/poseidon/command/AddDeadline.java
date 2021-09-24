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

public class AddDeadline extends Command {

    private static final String CMD_FORMAT = "(?i)deadline.*";
    private static final String CMD_VALID_FORMAT = "(?i)(deadline ).*\\S+.*( /by )\\d{4}\\s\\d{2}\\s\\d{2}\\s\\d{4}";

    public AddDeadline(String cmdContent) {
        super(cmdContent);
    }

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
