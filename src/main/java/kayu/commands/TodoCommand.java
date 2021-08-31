package kayu.commands;

import static kayu.commands.CommandMessage.MESSAGE_CREATED_TODO;
import static kayu.commands.CommandType.TODO;

import kayu.exception.DukeException;
import kayu.exception.StorageException;
import kayu.parser.DateTimeFormat;
import kayu.service.TaskList;
import kayu.storage.Storage;
import kayu.task.Task;
import kayu.task.Todo;

/**
 * Represents an {@link kayu.commands.AddTaskCommand} that creates a {@link kayu.task.Todo}
 * and saves it in {@link kayu.service.TaskList}.
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
    public String execute(TaskList taskList, Storage storage) throws DukeException, StorageException {
        String desc = super.extractDesc(new String[] {commandParams}, COMMAND_WORD);
        Task todo = new Todo(desc);
        taskList.addTask(todo);
        
        super.saveTasks(taskList, storage);
        return String.format(MESSAGE_CREATED_TODO, todo, taskList.getCapacity());
    }
}
