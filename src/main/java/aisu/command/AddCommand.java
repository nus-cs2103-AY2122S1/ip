package aisu.command;

import aisu.exception.AisuException;
import aisu.storage.Storage;
import aisu.tasklist.TaskList;
import aisu.ui.Ui;
import aisu.task.Task;

/**
 * Command to add a task to the tasklist.
 *
 * @author Liaw Xin Yan
 */
public class AddCommand extends Command {
    private final TaskList.TaskTypes taskType;
    private final String input;

    public AddCommand(TaskList.TaskTypes taskType, String input) {
        this.taskType = taskType;
        this.input = input;
    }

    /**
     * Adds task to tasklist depending on task type and updates tasklist.
     *
     * @param tasklist TaskList used in Aisu.
     * @param storage  Storage used in Aisu.
     * @param ui       User interface used in Aisu.
     * @throws AisuException If command fails to be executed.
     */
    @Override
    public void execute(TaskList tasklist, Storage storage, Ui ui) throws AisuException {
        Task newTask;
        switch (this.taskType) {
        case T:
            newTask = tasklist.addTask(this.input, TaskList.TaskTypes.T);
            break;
        case D:
            newTask = tasklist.addTask(this.input, TaskList.TaskTypes.D);
            break;
        case E:
            newTask = tasklist.addTask(this.input, TaskList.TaskTypes.E);
            break;
        default:
            throw new AisuException("Invalid input!");
        }
        storage.save(tasklist);
        this.uiText = ui.formatText(" Got it! I've added this task:",
                " - " + newTask,
                " Now you have " + tasklist.getListSize() + " task(s) in the list.\n");
    }

    /**
     * Checks if the command is an Exit command.
     *
     * @return True if it is an Exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
