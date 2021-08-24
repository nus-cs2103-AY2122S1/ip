package kayu.commands;

import static kayu.commands.CommandMessage.MESSAGE_CREATED_TODO;
import static kayu.commands.CommandType.TODO;

import kayu.exception.DukeException;
import kayu.service.TaskList;
import kayu.task.Task;
import kayu.task.Todo;
import kayu.parser.DateTimeFormat;

public class TodoCommand extends AddTaskCommand {
    
    public static final String COMMAND_WORD = "todo";

    public TodoCommand(String commandParams, DateTimeFormat dateTimeFormat) {
        super(TODO, commandParams, dateTimeFormat);
    }

    @Override
    public String execute(TaskList taskList) throws DukeException {
        String desc = super.extractDesc(new String[] {commandParams}, COMMAND_WORD);
        
        Task todo = new Todo(desc);
        taskList.addTask(todo);
        
        return String.format(MESSAGE_CREATED_TODO, todo, taskList.getCapacity());
    }
}
