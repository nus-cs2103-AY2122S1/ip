package commands;

import exception.DukeException;
import service.TaskList;
import task.Event;
import task.Task;
import utils.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventCommand extends AddCommand {

    public static final String COMMAND_WORD = "event";

    public EventCommand(String commandParams, DateTimeFormat dateTimeFormat) {
        super(CommandType.EVENT, commandParams, dateTimeFormat);
    }

    @Override
    public String execute(TaskList taskList) throws DukeException {
        String desc = super.extractDesc(commandParams, COMMAND_WORD, Event.SPLIT_WORD);
        String[] dateTime = super.extractDateTime(commandParams, COMMAND_WORD, Event.SPLIT_WORD);
        LocalDate atDate = super.extractDate(dateTime);
        LocalTime atTime = super.extractTime(dateTime);
        
        Task event = new Event(desc, atDate, atTime);
        taskList.addTask(event);
        return String.format(CommandMessage.MESSAGE_CREATED_EVENT, event, taskList.getCapacity());
    }
}
