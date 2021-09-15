package duke.command;

import java.io.IOException;

import duke.misc.TaskList;
import duke.misc.Ui;

/**
 * ByeCommand class which represents the command to exit the chat bot.
 */
public class ByeCommand extends Command {
    private boolean isBye;

    /**
     * Constructor for ByeCommand class.
     */
    public ByeCommand() {
        isBye = true;
    }

    /**
     * Executes the data saving operation.
     *
     * @param tl The TaskList which data will be saved.
     * @return String to notify user of successful command execution.
     * @throws IOException In case of errors.
     */
    public String execute(TaskList tl) throws IOException {
        tl.saveData();
        return Ui.GOODBYE_MSG;
    }

    public boolean getIsBye() {
        return isBye;
    }
}
