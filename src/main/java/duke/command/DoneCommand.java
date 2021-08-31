package duke.command;

import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;

import java.util.ArrayList;

/**
 * DoneCommand class which handles the logic of finishing tasks.
 */
public class DoneCommand extends Command {
    private final String fullCommand;

    /**
     * Constructor method of DoneCommand.
     *
     * @param fullCommand Full command given by the user.
     */
    public DoneCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public ArrayList<String> execute(TaskList taskList, Ui ui, Storage storage) {
        char lastDigit = fullCommand.charAt(fullCommand.length() - 1);
        int index = Integer.parseInt(String.valueOf(lastDigit));
        return taskList.setAsDone(index);
    }

}