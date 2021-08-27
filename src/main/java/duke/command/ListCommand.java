package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * This class represents a {@code ListCommand}. User input for a
 * {@code ListCommand} starts with "list".
 *
 * @author Elizabeth Chow
 */
public class ListCommand extends Command {
    /**
     * Constructor for {@code ListCommand}.
     */
    public ListCommand() {
        super();
    }

    /**
     * Shows all the tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTasks(tasks.getTasks());
    }

    /**
     * Returns {@code false}. Program should not terminate after {@code ListCommand}.
     *
     * @return {@code false}
     */
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ListCommand;
    }
}
