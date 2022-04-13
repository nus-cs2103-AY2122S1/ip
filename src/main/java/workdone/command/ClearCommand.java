package workdone.command;

import workdone.data.Storage;
import workdone.data.TaskList;

/**
 * Represents a command that deletes all task from the task list. A subclass of the Command class.
 */
public class ClearCommand extends Command {
    /**
     * Constructor of the class `ClearCommand`.
     */
    public ClearCommand() {
        super("clear");
    }

    /**
     * Executes the command. Deletes all tasks from the list, stores changes and updates the message to be
     * printed.
     *
     * @param tasks A list of tasks.
     * @param storage An instance of Storage that can read from and write to the hard disk.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        int len = tasks.getNumOfTasks();
        for (int i = 0; i < len; i++) {
            this.task = tasks.get(0);
            // Remove the task
            assert this.task != null : "task shouldn't be null";
            storage.removeFromFile(0);
            tasks.removeFromList(this.task);
        }

        // Update message
        this.message = "Noted. I've removed all the tasks.\n";
    }
}
