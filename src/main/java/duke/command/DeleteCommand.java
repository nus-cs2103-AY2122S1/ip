package duke.command;
import java.util.ArrayList;

import duke.DukeException;
import duke.Input;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Command to delete tasks.
 */
public class DeleteCommand extends Command {
    private Input input;
    private ArrayList<Integer> taskNumberList;

    /**
     * Constructor for DeleteCommand.
     *
     * @param input User input.
     * @throws DukeException If input is invalid.
     */
    public DeleteCommand(Input input) throws DukeException {
        this.input = input;
        if (input.hasCommandWordOnly("delete")) {
            throw new DukeException("A number must follow after the command word 'delete'.");
        }
    }

    /**
     * Removes task from current list.
     *
     * @param ls Current list.
     * @param ui Current Ui.
     * @param storage Current version of the saved tasks in the hard disk.
     * @throws DukeException If input is invalid.
     */
    @Override
    public String execute(TaskList ls, Ui ui, Storage storage) throws DukeException {

        try {
            this.taskNumberList = input.getIndexArray("delete", ls.getSize());
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS! Please enter a valid task number.");
        }

        TaskList tasksToBeRemoved = new TaskList();
        for (int taskNumber : this.taskNumberList) {
            Task task = ls.getTask(taskNumber);
            tasksToBeRemoved.addTask(task);
        }

        for (int i = 0; i < tasksToBeRemoved.getSize(); i++) {
            Task task = tasksToBeRemoved.getTask(i);
            ls.removeTask(task);
            storage.rewriteFile(ls);
        }

        return ui.removeTaskFromList(tasksToBeRemoved, ls.getSize());
    }

    /**
     * Signals to the system that the command is not an exit command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
