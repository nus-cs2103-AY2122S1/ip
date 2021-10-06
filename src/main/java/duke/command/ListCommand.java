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
     *
     * @param tasks   the tasklist to be modified.
     * @param ui      responsible for printing to the terminal.
     * @param storage stores all the tasks.
     *
     * @return String message to be displayed.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTasks(tasks.getTasks());
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ListCommand;
    }
}
