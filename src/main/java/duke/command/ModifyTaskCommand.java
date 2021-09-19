package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.IndexMismatchException;
import duke.task.Task;

/**
 * A class that represents a command that will modify tasks.
 */
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
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        if (!this.commandBody.matches("\\d+")) {
            throw new IndexMismatchException();
        }
        int item = Integer.parseInt(this.commandBody);
        Task task = taskList.getTask(item);

        assert task != null;
        switch (this.commandType) {
        case DONE: {
            taskList.completeTask(item);
            storage.refreshTask(taskList);
            return Ui.taskDoneMessage(task);
        }
        case UNDONE: {
            taskList.undoneTask(item);
            storage.refreshTask(taskList);
            return Ui.taskUndoneMessage(task);
        }
        case DELETE: {
            taskList.removeTask(item);
            storage.refreshTask(taskList);
            return Ui.removeTaskMessage(taskList, task);
        }
        default:
            assert false;
            throw new DukeException("Something wrong happened when executing Duke!");
        }
    }

    public enum CommandType {
        DONE, DELETE, UNDONE
    }
}
