package duke.Command;

import duke.DukeException;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

/**
 * Command to delete task from list
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs delete command
     * @param index Index of task to be deleted
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Delete task from list
     * @param tasks Current TaskList
     * @param ui Ui object of bot
     * @param storage Storage object of bot
     * @return Confirmation message
     * @throws DukeException when problem deleting task
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int size = tasks.size();
        Task task = tasks.deleteTask(index);
        assert tasks.size() == size - 1;
        String result = "";
        result += ("Deleted:\n" + task.getDescription() + "\n");
        result += ("There are " + tasks.size() + " tasks remaining in the list");
        storage.save(tasks);
        return result;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
