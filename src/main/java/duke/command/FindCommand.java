package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents command to find task from list.
 */
public class FindCommand implements Command {
    private final String DESCRIPTION;

    /**
     * Constructor for FindCommand.
     *
     * @param desc Description for Command
     */
    public FindCommand(String desc) {
        this.DESCRIPTION = desc;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return tasks.findTask(DESCRIPTION);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
