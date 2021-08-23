package duke.command;

import duke.task.Task;
import duke.task.ToDoTask;

public class AddToDoTaskCommand extends AddTaskCommand {

    private static final CommandType COMMAND_TYPE = CommandType.ADD_TODO_TASK;

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
