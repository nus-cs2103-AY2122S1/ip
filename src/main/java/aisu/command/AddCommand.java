package aisu.command;

import aisu.exception.AisuException;
import aisu.storage.Storage;
import aisu.task.Task;
import aisu.tasklist.TaskList;
import aisu.ui.Ui;

/**
 * Command to add a task to the tasklist.
 *
 * @author Liaw Xin Yan
 */
public class AddCommand extends Command {
    private final TaskList.TaskTypes taskType;
    private final String input;

    /**
     * Constructor to initialise the Add command.
     * @param taskType The type of task to add to the tasklist.
     * @param input The parsed user input, used as details for the task to be added.
     */
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
        this.uiText = Ui.formatText(" Got it! I've added this task:",
                " - " + newTask,
                " Now you have " + tasklist.getListSize() + " task(s) in the list.\n");
    }

    /** {@inheritDoc} */
    @Override
    public boolean isExit() {
        return false;
    }
}
