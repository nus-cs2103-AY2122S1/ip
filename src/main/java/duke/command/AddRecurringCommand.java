package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents command to add Deadline.
 */
public class AddRecurringCommand implements Command {
    private final String DESCRIPTION;

    /**
     * Constructor for AddDeadlineCommand.
     *
     * @param desc Description for Command
     */
    public AddRecurringCommand(String desc) {
        this.DESCRIPTION = desc;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return tasks.addRecurringTask(DESCRIPTION);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
