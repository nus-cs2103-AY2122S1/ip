package commands;

import exception.DukeException;
import service.TaskList;
import task.Deadline;
import task.Task;
import utils.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public class DeadlineCommand extends AddCommand {

    public static final String COMMAND_WORD = "deadline";

    public DeadlineCommand(String commandParams, DateTimeFormat dateTimeFormat) {
        super(CommandType.DEADLINE, commandParams, dateTimeFormat);
    }
    
    @Override
    public String execute(TaskList taskList) throws DukeException {
        String desc = super.extractDesc(commandParams, COMMAND_WORD, Deadline.SPLIT_WORD);
        String[] dateTime = super.extractDateTime(commandParams, COMMAND_WORD, Deadline.SPLIT_WORD);
        LocalDate byDate = super.extractDate(dateTime);
        LocalTime byTime = super.extractTime(dateTime);

        Task deadline = new Deadline(desc, byDate, byTime);
        taskList.addTask(deadline);
        return String.format(CommandMessage.MESSAGE_CREATED_DEADLINE, deadline, taskList.getCapacity());
    }
}

