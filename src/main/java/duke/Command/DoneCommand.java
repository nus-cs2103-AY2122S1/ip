package duke.Command;

import duke.DukeException;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

/**
 * Command to mark task as done
 */
public class DoneCommand extends Command {

    private int index;

    /**
     * Constructs done command
     * @param index Index of task to be marked as done
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Marks task as done
     * @param tasks Current TaskList
     * @param ui Ui object of bot
     * @param storage Storage object of bot
     * @return Confirmation message
     * @throws DukeException when problem saving tasks
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.doneTask(this.index);
        storage.save(tasks);
        return ("Marked as done:\n" + task.getDescription());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
