package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Response;

/**
 * The EditCommand class extends the Command class and is the Command that
 * edits a Task to the TaskList.
 */
public class EditCommand extends Command {

    /** The line to be edited. */
    private int lineNumber;

    /**
     * The constructor for the EditCommand object.
     *
     * @param commandType The Command type
     * @param lineNumber The line to be edited
     */
    public EditCommand(CommandType commandType, int lineNumber) {
        super(commandType, false);
        this.lineNumber = lineNumber;
    }

    /**
    * The execute command that executes the necessary actions when a Task
    * is edited in the TaskList.
    *
    * @param tasks The TaskList to be added to
    * @param response The Ui object to interact with the user
    * @param storage The Storage object that stores the TaskList on the Local Machine
    */
    public String execute(TaskList tasks, Response response, Storage storage) {
        Task editedTask = tasks.editTaskList(lineNumber, this.getCommandType());
        storage.editStorage(lineNumber, this.getCommandType());
        return response.showEditTaskMessage(editedTask, this.getCommandType(), tasks);
    }
}
