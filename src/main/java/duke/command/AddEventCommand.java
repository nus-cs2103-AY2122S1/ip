package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents command to add Event.
 */
public class AddEventCommand implements Command {
    private final String desc;

    /**
     * Constructor for AddEventCommand.
     *
     * @param desc Description for Command
     */
    public AddEventCommand(String desc) {
        this.desc = desc;
    }

    @Override
    public void execute(TaskList task, Ui ui, Storage storage) throws DukeException {
        task.addEvent(desc);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
