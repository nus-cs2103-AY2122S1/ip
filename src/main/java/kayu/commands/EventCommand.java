package kayu.commands;

import static kayu.commands.CommandMessage.MESSAGE_CREATED_EVENT;

import java.time.LocalDate;
import java.time.LocalTime;

import kayu.exception.KayuException;
import kayu.exception.StorageException;
import kayu.parser.DateTimeFormat;
import kayu.service.TaskList;
import kayu.storage.Storage;
import kayu.task.Event;
import kayu.task.Task;

/**
 * Represents an {@link kayu.commands.AddTaskCommand} that creates a {@link kayu.task.Event}
 * and saves it in {@link kayu.service.TaskList}.
 */
public class EventCommand extends AddTaskCommand {

    /** Keyword for command. */
    public static final String COMMAND_WORD = "event";

    /**
     * Initializes an Event- {@link kayu.commands.AddTaskCommand}.
     *
     * @param commandParams String parameters fed into the command by user.
     * @param dateTimeFormat {@link kayu.parser.DateTimeFormat} used in parsing, if required.
     */
    public EventCommand(String commandParams, DateTimeFormat dateTimeFormat) {
        super(commandParams, dateTimeFormat);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws KayuException, StorageException {
        String[] paramArray = getParamArray();
        Task event = createEvent(paramArray);
        super.updateTasks(taskList, storage, event);
        
        return String.format(MESSAGE_CREATED_EVENT, event, taskList.getCapacity());
    }
    
    private Task createEvent(String[] paramArray) throws KayuException {
        String desc = super.extractDesc(paramArray, COMMAND_WORD);
        LocalDate atDate = super.extractDate(paramArray);
        LocalTime atTime = super.extractTime(paramArray);

        return new Event(desc, atDate, atTime);
    }

    private String[] getParamArray() throws KayuException {
        return super.splitUserParams(commandParams, COMMAND_WORD, Event.SPLIT_WORD);
    }
}
