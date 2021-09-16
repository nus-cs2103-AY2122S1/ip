package duke.command;

import java.io.IOException;

import duke.misc.TaskList;
import duke.misc.Ui;

/**
 * FindCommand class to represent command to find specific tasks.
 */
public class FindCommand extends Command {
    private boolean isBye = false;
    private String inputSuffix;

    public FindCommand(String inputSuffix) {
        this.inputSuffix = inputSuffix;
    }

    /**
     * Executes the operation to find specified tasks.
     *
     * @param tl The current TaskList.
     * @return String to notify user of successful command execution.
     * @throws IOException In case of invalid file directory.
     */
    public String execute(TaskList tl) throws IOException {
        return Ui.FIND_MSG + tl.findTasks(inputSuffix);
    }

    public boolean getIsBye() {
        return isBye;
    }
}
