package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents command to mark task as done.
 */
public class DoneCommand implements Command {
    private final String desc;

    /**
     * Constructor for DoneCommand.
     *
     * @param desc Description for Command
     */
    public DoneCommand(String desc) {
        this.desc = desc;
    }

    @Override
    public void execute(TaskList task, Ui ui, Storage storage) throws DukeException {
        task.markDone(desc);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
