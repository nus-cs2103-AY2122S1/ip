package ligma.command;

import ligma.Storage;
import ligma.TaskList;
import ligma.Ui;
import ligma.task.Task;

import java.io.IOException;

/**
 * This class represents a command to mark a task as completed.
 */
public class DoneCommand implements Command {
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Updates completion status of tasks from tasklist
     * and storage as well as reflect execution status on UI.
     *
     * @param tasks     all tasks belonging to current Ligma program
     * @param storage   storage of current Ligma program
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        Task t = tasks.markAsDone(index);

        try {
            storage.markDone(t);
            Ui.printSuccessMessage("marked as done:\n " + t);
        } catch (IOException e) {
            Ui.printErrorMessage("Failed to update task in storage: \n" + t);
        }
    }

    /**
     * Returns true if command is an exit command.
     *
     * @return true if command is an exit command, false otherwise
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
