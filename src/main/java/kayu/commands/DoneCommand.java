package kayu.commands;

import kayu.exception.DukeException;
import kayu.service.TaskList;
import kayu.task.Task;

/**
 * EmptyCommand class.
 *
 * This class is an instance of {@link kayu.commands.Command} that uses the keyword {@link #COMMAND_WORD}. 
 * It is used when the user wants a certain {@link kayu.task.Task} to be marked as done.
 */
public class DoneCommand extends Command {
    
    public static final String COMMAND_WORD = "done";

    public DoneCommand(String commandParams) {
        super(CommandType.DONE, commandParams);
    }

    /**
     * See {@link kayu.commands.Command#execute(TaskList)}.
     */
    @Override
    public String execute(TaskList taskList) throws DukeException {
        try {
            int taskNumber = Integer.parseInt(commandParams);
            Task selectedTask = taskList.updateTaskAsDone(taskNumber);
            return String.format(CommandMessage.MESSAGE_TASK_DONE, selectedTask);

        } catch (NumberFormatException exception) {
            throw new DukeException(String.format(CommandMessage.ERROR_NOT_AN_INT_PARAM, commandParams));
        }
    }
}
