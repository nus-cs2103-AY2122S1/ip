package kayu.commands;

import static kayu.commands.CommandMessage.MESSAGE_CREATED_TODO;
import static kayu.commands.CommandType.TODO;

import kayu.exception.DukeException;
import kayu.service.TaskList;
import kayu.task.Task;
import kayu.task.Todo;
import kayu.parser.DateTimeFormat;

/**
 * TodoCommand class.
 * 
 * This class is an instance of {@link kayu.commands.AddTaskCommand} that uses the 
 * keyword {@link #COMMAND_WORD}. It creates a {@link kayu.task.Todo} and saves it.
 */
public class TodoCommand extends AddTaskCommand {

    /** Key word for command. */
    public static final String COMMAND_WORD = "todo";

    /**
     * Initializes a Todo- {@link kayu.commands.AddTaskCommand}.
     *
     * @param commandParams String parameters fed into the command by user.
     * @param dateTimeFormat {@link kayu.parser.DateTimeFormat} used in parsing, if required.
     */
    public TodoCommand(String commandParams, DateTimeFormat dateTimeFormat) {
        super(TODO, commandParams, dateTimeFormat);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList) throws DukeException {
        String desc = super.extractDesc(new String[] {commandParams}, COMMAND_WORD);
        
        Task todo = new Todo(desc);
        taskList.addTask(todo);
        
        return String.format(MESSAGE_CREATED_TODO, todo, taskList.getCapacity());
    }
}
