package duke.command;

import duke.exceptions.DukeIndexException;
import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;

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
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            int index = Integer.parseInt(fullCommand.split(" ")[1]);
            taskList.deleteTask(index);
        } catch (DukeIndexException e) {
            System.err.println(e.getMessage());
        }
    }


}