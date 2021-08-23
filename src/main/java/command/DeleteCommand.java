package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import task.Task;

public class DeleteCommand extends Command{
    /** The index of the task to be deleted. **/
    public int taskIndex;

    /**
     * A public constructor to initialized the DeleteCommand.
     *
     * @param taskIndex The index of the task to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * A method to execute this DeleteCommand. When the method is executed,
     * the corresponding task will be removed from the given TaskList,
     * and message will be send to the given Ui.
     *
     * @param taskList The given Duke TaskList.
     * @param ui The given Duke Ui.
     * @param storage The given Duke Storage.
     * @throws DukeException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task deletedTask = taskList.delete(this.taskIndex);
        ui.printMessage(new String[] {
                "Noted. I've removed this task:",
                "  " + deletedTask.toString(),
                "Now you have " + taskList.amountOfTasks() + " tasks in the list."});
    }
}
