package ligma.command;

import ligma.Storage;
import ligma.TaskList;
import ligma.Ui;
import ligma.task.Task;

import java.io.IOException;

/**
 * This class represents a command to delete a task.
 */
public class DeleteCommand implements Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes tasks from tasklist and storage as well as reflect execution status on UI.
     *
     * @param tasks     all tasks belonging to current Ligma program
     * @param storage   storage of current Ligma program
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        Task t = tasks.deleteTask(index);

        try {
            storage.deleteTask(t);
            Ui.printSuccessMessage("deleted:\n " + t
                    + String.format("\n You now have %d task(s).", tasks.getTaskAmt()));
        } catch (IOException e) {
            Ui.printErrorMessage("Failed to delete task from storage: \n" + t);
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
