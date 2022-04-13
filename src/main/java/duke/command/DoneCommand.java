package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * A class to encapsulate the command of
 * marking a task as done.
 */
public class DoneCommand extends Command {

    /** The index of the task in the TaskList */
    private int taskNum;

    /**
     * A public constructor which initializes the command
     * and task number to the given one.
     *
     * @param command The command inputted by the user.
     * @param taskNum The index of the task.
     */
    public DoneCommand(String command, int taskNum) {
        super(command);
        this.taskNum = taskNum;
    }

    /**
     * Executes the done command inputted by the user,
     * by marking the specified task as done.
     *
     * @param tasks   The list of tasks stored so far.
     * @param ui      The Ui to deal with interactions with user.
     * @param storage The storage which saves and edits file content.
     * @return The string indicating the command has been executed.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task temp = tasks.get(taskNum - 1);
        temp.markAsDone();
        try {
            storage.editFileAll(tasks);
        } catch (DukeException e) {
            return e.getMessage();
        }
        return ui.doneTask(temp);
    }
}
