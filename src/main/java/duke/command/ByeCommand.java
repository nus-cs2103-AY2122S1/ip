package duke.command;

import java.io.IOException;

import duke.misc.TaskList;
import duke.misc.Ui;

/**
 * ByeCommand class which represents the command to exit Duke.
 */
public class ByeCommand extends Command {
    private final boolean isBye;

    /**
     * Constructor for ByeCommand class.
     */
    public ByeCommand() {
        isBye = true;
    }

    /**
     * Executes the data saving operation.
     *
     * @param tl The current TaskList.
     * @return String to notify user of successful command execution.
     * @throws IOException In case of invalid file directory.
     */
    public String execute(TaskList tl) throws IOException {
        tl.saveData();
        return Ui.GOODBYE_MSG;
    }

    public boolean getIsBye() {
        return isBye;
    }
}
