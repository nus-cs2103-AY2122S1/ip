import java.io.IOException;

/**
 * The DeleteCommand class encapsulates the execution of the delete command from the user.
 */
public class DeleteCommand extends Command {
    /** The delete command inputted by the user. */
    String deleteDescription;

    /**
     * Constructor for the DeleteCommand.
     * @param deleteDescription The delete command inputted by the user.
     */
    public DeleteCommand(String deleteDescription) {
        this.deleteDescription = deleteDescription;
    }

    /**
     * Executes the response to the delete command from the user.
     * @param storage The storage Duke uses to save and load the tasklist from.
     * @param taskList The list of tasks Duke needs to execute on.
     * @param ui The Ui Duke utilises to interact with the user.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws IOException {
        Integer taskNo = Integer.parseInt(deleteDescription);
        Task deletedTask = taskList.getSpecificTask(taskNo);
        taskList.deleteTask(taskNo);
        storage.saveFile(taskList.getAllTasks());
        ui.showTaskDeleted(deletedTask, taskList);
    }
}
