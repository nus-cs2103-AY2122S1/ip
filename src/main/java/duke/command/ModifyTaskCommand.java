package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.IndexMismatchException;
import duke.task.Task;

public class ModifyTaskCommand extends Command {
    /**
     * The type of the command.
     */
    private final CommandType commandType;

    /**
     * Constructs a {@code Command} object.
     *
     * @param commandType The command type.
     * @param commandBody The command body.
     */
    public ModifyTaskCommand(CommandType commandType, String commandBody) {
        super(commandBody);
        this.commandType = commandType;
    }

    /**
     * Executes the command by modifying a current task in the task list and the storage.
     *
     * @param taskList The task list that will be modified.
     * @param storage  The storage that will be modified.
     * @throws IndexMismatchException If the command body cannot be parsed as a positive integer.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) throws IndexMismatchException {
        if (!this.commandBody.matches("\\d+")) {
            throw new IndexMismatchException();
        }
        int item = Integer.parseInt(this.commandBody);
        Task task = taskList.getTask(item);

        switch (this.commandType) {
        case DONE: {
            taskList.completeTask(item);
            storage.refreshTask(taskList);
            Ui.taskDoneMessage(task);
            break;
        }
        case DELETE: {
            taskList.removeTask(item);
            storage.refreshTask(taskList);
            Ui.removeTaskMessage(taskList, task);
            break;
        }
        }
    }

    public enum CommandType {
        DONE, DELETE
    }
}
