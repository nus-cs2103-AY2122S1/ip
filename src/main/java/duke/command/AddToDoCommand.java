package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents command to add ToDo.
 */
public class AddToDoCommand implements Command {
    private final String DESCRIPTION;

    /**
     * Constructor for AddToDoCommand.
     *
     * @param desc Description for Command
     */
    public AddToDoCommand(String desc) {
        this.DESCRIPTION = desc;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return tasks.addToDo(DESCRIPTION);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
