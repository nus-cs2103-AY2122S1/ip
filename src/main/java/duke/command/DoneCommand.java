package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Contains the executables when the user uses the 'done' command.
 */
public class DoneCommand extends Command {
    private int taskNum;
    /**
     * Constructor for the done command.
     * @param taskNumber the task that is to be marked as done
     */
    public DoneCommand(int taskNumber) {
        this.taskNum = taskNumber;
    }

    /**
     * Executes the done command.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.done(taskNum);
        storage.save(taskList);
        try {
            return ui.doneTask(taskList.getAllTasks().get(taskNum));
        } catch (IndexOutOfBoundsException ioobe) {
            return "That task doesn't exist!";
        }
    }
}
