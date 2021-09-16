package duke.command;

import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * A class that represents a command that will add tasks.
 */
public class AddTaskCommand extends Command {
    /**
     * The type of the task.
     */
    private final Task.TaskType taskType;

    /**
     * Constructs an {@code AddTaskCommand} object.
     *
     * @param taskType The type of the task.
     * @param commandBody The command body.
     */
    public AddTaskCommand(Task.TaskType taskType, String commandBody) {
        super(commandBody);
        this.taskType = taskType;
    }

    /**
     * Executes the command by adding a new task.
     *
     * @param taskList The task list to which a task will be added.
     * @param storage  The storage in which a task will be added.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        Task task = Parser.descriptionToTask(this.taskType, this.commandBody);
        taskList.addTask(task);
        storage.addTask(task);
        return Ui.addTaskMessage(taskList, task);
    }
}
