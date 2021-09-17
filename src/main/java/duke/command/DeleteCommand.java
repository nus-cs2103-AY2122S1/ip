package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * The DeleteCommand class encapsulates the execution of the delete command from the user.
 */
public class DeleteCommand extends Command {

    /** The delete command index inputted by the user. */
    private String deleteDescription;

    /**
     * Constructor for the DeleteCommand.
     *
     * @param deleteDescription The delete command index inputted by the user.
     */
    public DeleteCommand(String deleteDescription) {
        this.deleteDescription = deleteDescription;
    }

    /**
     * Executes the response to the delete command from the user.
     *
     * @param storage The storage Duke uses to save and load the tasklist from.
     * @param taskList The list of tasks Duke needs to execute on.
     * @param ui The Ui Duke utilises to interact with the user.
     * @return The String to be printed in the Duke GUI.
     * @throws IOException If there is an exception relating to the input and output.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws IOException {

        Integer taskNo = Integer.parseInt(deleteDescription);
        Task deletedTask = taskList.getSpecificTask(taskNo);
        taskList.deleteTask(taskNo);
        storage.saveFile(taskList.getAllTasks());
        return ui.showTaskDeleted(deletedTask, taskList);

    }
}
