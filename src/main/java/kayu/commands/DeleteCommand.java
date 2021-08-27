package kayu.commands;

import static kayu.commands.CommandMessage.ERROR_NOT_AN_INT_PARAM;
import static kayu.commands.CommandMessage.MESSAGE_DELETED_TASK;
import static kayu.commands.CommandType.DELETE;

import kayu.exception.DukeException;
import kayu.service.TaskList;
import kayu.task.Task;

/**
 * EmptyCommand class.
 *
 * This class is an instance of {@link kayu.commands.Command} that uses the keyword {@link #COMMAND_WORD}. 
 * It is used when the user wants a certain {@link kayu.task.Task} to be deleted.
 */
public class DeleteCommand extends Command {

    /** Key word for command. */
    public static final String COMMAND_WORD = "delete";


    /**
     * Initializes a Delete- {@link kayu.commands.Command}.
     *
     * @param commandParams String parameters fed into the command by user.
     */
    public DeleteCommand(String commandParams) {
        super(DELETE, commandParams);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList) throws DukeException {
        try {
            int taskNumber = Integer.parseInt(commandParams);
            Task selectedTask = taskList.deleteTask(taskNumber);
            
            return String.format(MESSAGE_DELETED_TASK, selectedTask, taskList.getCapacity());
            
        } catch (NumberFormatException exception) {
            throw new DukeException(String.format(ERROR_NOT_AN_INT_PARAM, commandParams));
        }
    }
}
