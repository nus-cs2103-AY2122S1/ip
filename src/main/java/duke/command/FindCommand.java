package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents command to find task from list.
 */
public class FindCommand implements Command {
    private final String desc;

    /**
     * Constructor for FindCommand.
     *
     * @param desc Description for Command
     */
    public FindCommand(String desc) {
        this.desc = desc;
    }

    @Override
    public void execute(TaskList task, Ui ui, Storage storage) throws DukeException {
        task.findTask(desc);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
