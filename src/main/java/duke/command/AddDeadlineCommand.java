package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents command to add Deadline.
 */
public class AddDeadlineCommand implements Command {
    private final String desc;

    /**
     * Constructor for AddDeadlineCommand.
     *
     * @param desc Description for Command
     */
    public AddDeadlineCommand(String desc) {
        this.desc = desc;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return tasks.addDeadline(desc);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
