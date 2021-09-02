package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * The DoneCommand class encapsulates the execution of the done command from the user.
 */
public class DoneCommand extends Command {

    /** The done command inputted by the user. */
    private String doneDescription;

    /**
     * Constructor for the DoneCommand.
     *
     * @param doneDescription The delete command inputted by the user.
     */
    public DoneCommand(String doneDescription) {
        this.doneDescription = doneDescription;
    }

    /**
     * Executes the response to the delete command from the user.
     *
     * @param storage The storage Duke uses to save and load the tasklist from.
     * @param taskList The list of tasks Duke needs to execute on.
     * @param ui The Ui Duke utilises to interact with the user.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws IOException {
        Integer taskNo = Integer.parseInt(doneDescription);
        Task doneTask = taskList.getSpecificTask(taskNo);
        taskList.doneTask(taskNo);
        storage.saveFile(taskList.getAllTasks());
        String output = ui.showTaskDone(doneTask);
        return output;
    }
}
