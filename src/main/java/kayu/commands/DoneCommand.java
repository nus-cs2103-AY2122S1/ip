package kayu.commands;

import static kayu.commands.CommandMessage.ERROR_NOT_AN_INT_PARAM;
import static kayu.commands.CommandMessage.MESSAGE_TASK_DONE;
import static kayu.commands.CommandType.DONE;

import kayu.exception.DukeException;
import kayu.service.TaskList;
import kayu.task.Task;

public class DoneCommand extends Command {
    
    public static final String COMMAND_WORD = "done";

    public DoneCommand(String commandParams) {
        super(DONE, commandParams);
    }

    @Override
    public String execute(TaskList taskList) throws DukeException {
        try {
            int taskNumber = Integer.parseInt(commandParams);
            Task selectedTask = taskList.updateTaskAsDone(taskNumber);
            
            return String.format(MESSAGE_TASK_DONE, selectedTask);

        } catch (NumberFormatException exception) {
            throw new DukeException(String.format(ERROR_NOT_AN_INT_PARAM, commandParams));
        }
    }
}
