package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The RemoveCommand is given when the user wants to remove a Task from the TaskList.
 */
public class RemoveCommand extends Command {

    public static final String COMMAND_WORD = "remove";
    private int taskNum;

    public RemoveCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Removes the Task corresponding to a given index from the given TaskList.
     *
     * @param tasks the given TaskList.
     * @param ui the given Ui.
     * @param storage the given Storage.
     * @return the string for the Ui to print.
     * @throws DukeException when the given index is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskNum >= tasks.numTasks()) {
            throw new DukeException("you typed an invalid number: " + (taskNum + 1));
        }
        String msg;

        Task removed = tasks.remove(taskNum);
        storage.save(tasks);
        msg = "Noted. I've removed this task:\n  " + removed + "\nNow you have " + tasks.numTasks();
        msg = tasks.numTasks() == 1 ? msg + " task in the list" : msg + " tasks in the list.";
        return msg;
    }
}
