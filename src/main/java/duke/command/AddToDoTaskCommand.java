package duke.command;

import duke.task.Task;
import duke.task.ToDoTask;

/**
 * Represents an "Add To-do Task" command.
 *
 * @author Jay Aljelo Saez Ting
 */
public class AddToDoTaskCommand extends AddTaskCommand {

    private static final CommandType COMMAND_TYPE = CommandType.ADD_TODO_TASK;

    /**
     * Creates an AddToDoTaskCommand instance.
     *
     * @param command The command represented by the instance.
     */
    public AddToDoTaskCommand(String command) {
        super(command);
    }

    @Override
    Task createTask() {
        return new ToDoTask(getTaskDescription());
    }

    @Override
    CommandType getCommandType() {
        return COMMAND_TYPE;
    }
}
