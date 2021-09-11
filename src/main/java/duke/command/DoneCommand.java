package duke.command;

import duke.exception.NoSuchTaskException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to mark a task in the task list as done.
 *
 * @author Cheong Yee Ming
 * @version Duke Level-10
 */
public class DoneCommand extends Command {

    private final int taskNumber;

    /**
     * Constructor for a DoneCommand.
     *
     * @param taskList   Handles all operations regarding tasks.
     * @param storage    Save and load data from local directory.
     * @param ui         Prints message with respect to user input.
     * @param taskNumber Task number of task to be marked as done.
     */
    public DoneCommand(TaskList taskList, Storage storage, Ui ui, int taskNumber) {
        super(taskList, storage, ui);
        this.taskNumber = taskNumber;
    }

    /**
     * Executes runCommand. Marks the task as done,
     * prints a message to tell user that task has been marked as done
     * and update local data file.
     *
     * @return String representation when Duke successfully marks a task as done.
     */
    @Override
    public String runCommand() throws NoSuchTaskException {
        assert taskNumber <= taskList.size() : "There is no such task.";
        storage.save(taskList.getList());
        return ui.guiTaskDoneMessage(taskList.markAsDone(taskNumber));
    }
}
