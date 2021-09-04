package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
/**
 * Contains the executables when the user uses the 'delete' command.
 *
 * @author Benjamin Lui
 */
public class DeleteCommand extends Command {

    private final int taskNumber;

    /**
     * Constructor for the delete command class
     * @param taskNumber the task to be deleted
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the delete command.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Task removed = taskList.delete(taskNumber);
        storage.save(taskList);
        return ui.deletedMsg() + ui.showTask(removed) + ui.showListLength(taskList);
    }
}
