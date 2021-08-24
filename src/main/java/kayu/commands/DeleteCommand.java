package kayu.commands;

import kayu.exception.DukeException;
import kayu.service.TaskList;
import kayu.task.Task;

public class DeleteCommand extends Command {
        
    public static final String COMMAND_WORD = "delete";

    public DeleteCommand(String commandParams) {
        super(CommandType.DELETE, commandParams);
    }

    @Override
    public String execute(TaskList taskList) throws DukeException {
        try {
            int taskNumber = Integer.parseInt(commandParams);
            Task selectedTask = taskList.deleteTask(taskNumber);
            
            return String.format(CommandMessage.MESSAGE_DELETED_TASK, selectedTask, taskList.getCapacity());
            
        } catch (NumberFormatException exception) {
            throw new DukeException(String.format(CommandMessage.ERROR_NOT_AN_INT_PARAM, commandParams));
        }
    }
}
