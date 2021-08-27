package kayu.commands;

import static kayu.commands.CommandMessage.MESSAGE_CREATED_EVENT;
import static kayu.commands.CommandType.EVENT;

import kayu.exception.DukeException;
import kayu.service.TaskList;
import kayu.task.Event;
import kayu.task.Task;
import kayu.parser.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * EventCommand class.
 *
 * This class is an instance of {@link kayu.commands.AddTaskCommand} that uses the keyword 
 * {@link #COMMAND_WORD}. It creates a {@link kayu.task.Event} and saves it.
 */
public class EventCommand extends AddTaskCommand {

    /** Key word for command. */
    public static final String COMMAND_WORD = "event";

    /**
     * Initializes a Event- {@link kayu.commands.AddTaskCommand}.
     *
     * @param commandParams String parameters fed into the command by user.
     * @param dateTimeFormat {@link kayu.parser.DateTimeFormat} used in parsing, if required.
     */
    public EventCommand(String commandParams, DateTimeFormat dateTimeFormat) {
        super(EVENT, commandParams, dateTimeFormat);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList) throws DukeException {
        String[] paramArray = super.splitUserParams(commandParams, COMMAND_WORD, Event.SPLIT_WORD);
        
        String desc = super.extractDesc(paramArray, COMMAND_WORD);
        LocalDate atDate = super.extractDate(paramArray);
        LocalTime atTime = super.extractTime(paramArray);
        
        Task event = new Event(desc, atDate, atTime);
        taskList.addTask(event);
        return String.format(MESSAGE_CREATED_EVENT, event, taskList.getCapacity());
    }
}
