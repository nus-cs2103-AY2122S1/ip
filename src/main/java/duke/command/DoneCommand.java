package duke.command;
import java.util.ArrayList;

import duke.DukeException;
import duke.Input;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Command to mark tasks as done.
 */
public class DoneCommand extends Command {
    private Input input;
    private ArrayList<Integer> taskNumberList;

    /**
     * Constructor for DoneCommand.
     *
     * @param input User input.
     * @throws DukeException If input is invalid.
     */
    public DoneCommand(Input input) throws DukeException {
        this.input = input;
        if (input.hasCommandWordOnly("done")) {
            throw new DukeException("A number must follow after the command word 'done'.");
        }
    }

    /**
     * Marks item as done.
     *
     * @param ls Current list.
     * @param ui Current Ui.
     * @param storage Current version of the saved tasks in the hard disk.
     * @throws DukeException If input is invalid.
     */
    @Override
    public String execute(TaskList ls, Ui ui, Storage storage) throws DukeException {

        try {
            this.taskNumberList = input.getIndexArray("done", ls.getSize());
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS! Please enter a valid task number.");
        }

        TaskList tasksToBeSetAsDone = new TaskList();

        for (int taskNumber : this.taskNumberList) {
            Task task = ls.getTask(taskNumber);
            tasksToBeSetAsDone.addTask(task);
        }

        for (int i = 0; i < tasksToBeSetAsDone.getSize(); i++) {
            Task task = tasksToBeSetAsDone.getTask(i);
            task.setDone();
            storage.rewriteFile(ls);
        }

        return ui.setTaskAsDone(tasksToBeSetAsDone);
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
