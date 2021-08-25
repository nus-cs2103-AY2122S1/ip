package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents command to add ToDo.
 */
public class AddToDoCommand implements Command {
    private final String desc;

    /**
     * Constructor for AddToDoCommand.
     *
     * @param desc Description for Command
     */
    public AddToDoCommand(String desc) {
        this.desc = desc;
    }

    @Override
    public void execute(TaskList task, Ui ui, Storage storage) throws DukeException {
        task.addToDo(desc);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
