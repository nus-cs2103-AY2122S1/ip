package duke.commands;

import duke.DukeException;
import duke.DukeStorage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

/**
 * Class that contains the done command
 *
 */
public class DoneCommand extends Command {

    /** The index of the task to be marked done */
    private int index;

    /**
     * Constructor for the done command class
     *
     * @param index The index of the task to be marked done
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the done command
     *
     * @param taskList The list of tasks that is associated with the instance of Duke
     * @param ui The UI that is associated with the instance of Duke
     * @param storage The storage that is associated with the instance of Duke
     * @return Duke's String response
     * @throws DukeException
     */
    @Override
    public String execute(TaskList taskList, Ui ui, DukeStorage storage) throws DukeException {
        if (this.index < 1 || this.index > taskList.size()) {
            throw new DukeException("OOPS!!! Please enter a valid index number :(\n");
        }

        Task done = taskList.setDone(index);
        return ui.doneMessage(done);
    }
}
