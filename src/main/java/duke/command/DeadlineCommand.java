package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * This class represents a {@code DeadlineCommand}. User input for a
 * {@code DeadlineCommand} starts with "deadline".
 * 
 * @author Elizabeth Chow
 */
public class DeadlineCommand extends Command {
    /**
     * Constructor for a {@code DeadlineCommand}
     * 
     * @param args {@code String} array with length 2. {@code args[0]} contains the
     *             title, {@code args[1]} contains the endDate for a
     *             {@code Deadline}.
     */
    public DeadlineCommand(String[] args) {
        super(args);
    }

    /**
     * {@inheritDoc} Adds {@code Deadline} to tasks and writes to storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addDeadlineTask(args[0], args[1]);
        storage.writeToFile(tasks);
        ui.showNewTask(tasks.lastTask(), tasks.size());
    }

    /**
     * Returns {@code false}. Program should not terminate after {@code DeadlineCommand}.
     * 
     * @return {@code false}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
