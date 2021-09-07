package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents command to delete Task.
 */
public class DeleteCommand implements Command {
    private final String DESCRIPTION;

    /**
     * Constructor for DeleteCommand.
     *
     * @param desc Description for Command
     */
    public DeleteCommand(String desc) {
        this.DESCRIPTION = desc;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return tasks.deleteTask(DESCRIPTION);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
