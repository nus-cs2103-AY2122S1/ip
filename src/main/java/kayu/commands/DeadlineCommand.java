package kayu.commands;

import kayu.exception.DukeException;
import kayu.service.TaskList;
import kayu.task.Deadline;
import kayu.task.Task;
import kayu.parser.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public class DeadlineCommand extends AddTaskCommand {

    public static final String COMMAND_WORD = "deadline";

    public DeadlineCommand(String commandParams, DateTimeFormat dateTimeFormat) {
        super(CommandType.DEADLINE, commandParams, dateTimeFormat);
    }
    
    @Override
    public String execute(TaskList taskList) throws DukeException {
        String[] paramArray = super.splitUserParams(commandParams, COMMAND_WORD, Deadline.SPLIT_WORD);
        
        String desc = super.extractDesc(paramArray, COMMAND_WORD);
        LocalDate byDate = super.extractDate(paramArray);
        LocalTime byTime = super.extractTime(paramArray);

        Task deadline = new Deadline(desc, byDate, byTime);
        taskList.addTask(deadline);
        
        return String.format(CommandMessage.MESSAGE_CREATED_DEADLINE, deadline, taskList.getCapacity());
    }
}

