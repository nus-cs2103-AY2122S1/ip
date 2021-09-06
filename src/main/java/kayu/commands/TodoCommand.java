package kayu.commands;

import static kayu.commands.CommandMessage.MESSAGE_CREATED_TODO;

import kayu.exception.KayuException;
import kayu.exception.StorageException;
import kayu.service.TaskList;
import kayu.storage.Storage;
import kayu.task.Task;
import kayu.task.Todo;

/**
 * Represents an {@link kayu.commands.AddTaskCommand} that creates a {@link kayu.task.Todo}
 * and saves it in {@link kayu.service.TaskList}.
 */
public class TodoCommand extends AddTaskCommand {

    /** Keyword for command. */
    public static final String COMMAND_WORD = "todo";

    /**
     * Initializes a Todo- {@link kayu.commands.AddTaskCommand}.
     *
     * @param commandParams String parameters fed into the command by user.
     */
    public TodoCommand(String commandParams) {
        super(commandParams);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws KayuException, StorageException {
        Task todo = createTask();
        super.updateTasks(taskList, storage, todo);
        
        return String.format(MESSAGE_CREATED_TODO, todo, taskList.getCurrentCapacity());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task createTask() throws KayuException {
        String desc = extractDesc(commandParams);
        return new Todo(desc);
    }
    
    // call on wrapper method in super class
    protected String extractDesc(String params) throws KayuException {
        return super.extractDesc(new String[] {params}, COMMAND_WORD);
    }
}
