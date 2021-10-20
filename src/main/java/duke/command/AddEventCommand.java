package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents command to add Event.
 */
public class AddEventCommand implements Command {
    private final String DESCRIPTION;

    /**
     * Constructor for AddEventCommand.
     *
     * @param desc Description for Command
     */
    public AddEventCommand(String desc) {
        this.DESCRIPTION = desc;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return tasks.addEvent(DESCRIPTION);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
