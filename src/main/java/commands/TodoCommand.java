package commands;

import exception.DukeException;
import service.TaskList;
import task.Task;
import task.Todo;

public class TodoCommand extends AddCommand {
    
    public static final String COMMAND_WORD = "todo";

    public TodoCommand(String commandParams) {
        super(CommandType.TODO, commandParams);
    }

    @Override
    public String execute(TaskList taskList) throws DukeException {
        String desc = super.extractDesc(commandParams, COMMAND_WORD, null);
        Task todo = new Todo(desc);
        taskList.addTask(todo);
        return String.format(CommandMessage.MESSAGE_CREATED_TODO, todo, taskList.getCapacity());
    }
}
