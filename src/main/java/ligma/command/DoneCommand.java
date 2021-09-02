package ligma.command;

import java.io.IOException;

import ligma.Storage;
import ligma.TaskList;
import ligma.ui.Ui;
import ligma.task.Task;

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
    public String execute(TaskList tasks, Storage storage) {
        Task t = tasks.markAsDone(index);

        try {
            storage.markDone(t);
            return Ui.getSuccessMessage("marked as done:\n " + t);
        } catch (IOException e) {
            return "Failed to update task in storage: \n" + t;
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
