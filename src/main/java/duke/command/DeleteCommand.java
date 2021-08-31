package duke.command;

import duke.exceptions.DukeIndexException;
import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;

import java.util.ArrayList;

/**
 * DeleteCommand class which handles the logic of deleting tasks.
 */
public class DeleteCommand extends Command {
    private final String fullCommand;

    /**
     * Constructor method of DeleteCommand.
     *
     * @param fullCommand Full command given by the user.
     */
    public DeleteCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public ArrayList<String> execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            int index = Integer.parseInt(fullCommand.split(" ")[1]);
            return taskList.deleteTask(index);
        } catch (DukeIndexException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }


}