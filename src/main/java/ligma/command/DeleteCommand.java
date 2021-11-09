package ligma.command;

import java.io.IOException;

import ligma.Storage;
import ligma.TaskList;
import ligma.ui.Ui;
import ligma.task.Task;

/**
 * This class represents a command to delete a task.
 */
public class DeleteCommand implements Command {
    private int index;

    public DeleteCommand(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Index is must be more than zero.");
        }
        this.index = index;
    }

    /**
     * Deletes tasks from tasklist and storage as well as reflect execution status on UI.
     *
     * @param tasks     all tasks belonging to current Ligma program
     * @param storage   storage of current Ligma program
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        if (index > tasks.getTaskAmt()) {
            throw new IllegalArgumentException("Index exceeds number of tasks present.");
        }
        Task t = tasks.deleteTask(index);

        try {
            storage.saveTaskList(tasks);
            return Ui.getSuccessMessage("deleted:\n" + t
                    + String.format("\n You now have %d task(s).", tasks.getTaskAmt()));
        } catch (IOException e) {
            return "Failed to delete task from storage: \n" + t;
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
