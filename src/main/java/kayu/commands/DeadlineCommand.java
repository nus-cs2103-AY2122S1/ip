package kayu.commands;

import static kayu.commands.CommandMessage.MESSAGE_CREATED_DEADLINE;

import java.time.LocalDate;
import java.time.LocalTime;

import kayu.exception.KayuException;
import kayu.exception.StorageException;
import kayu.service.TaskList;
import kayu.storage.Storage;
import kayu.task.Deadline;
import kayu.task.Task;

/**
 * Represents an {@link kayu.commands.AddTaskCommand} that creates a {@link kayu.task.Deadline}
 * and saves it in {@link kayu.service.TaskList}.
 */
public class DeadlineCommand extends AddTaskCommand {

    /** Keyword for command. */
    public static final String COMMAND_WORD = "deadline";

    /**
     * Initializes a Deadline- {@link kayu.commands.AddTaskCommand}.
     *
     * @param commandParams String parameters fed into the command by user.
     */
    public DeadlineCommand(String commandParams) {
        super(commandParams);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws KayuException, StorageException {
        Task deadline = createTask();
        super.updateTasks(taskList, storage, deadline);
        
        return String.format(MESSAGE_CREATED_DEADLINE, deadline, taskList.getCurrentCapacity());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task createTask() throws KayuException {
        String[] paramArray = getParamArray();
        String desc = super.extractDesc(paramArray, COMMAND_WORD);
        LocalDate byDate = super.extractDate(paramArray);
        LocalTime byTime = super.extractTime(paramArray);

        return new Deadline(desc, byDate, byTime);
    }
    
    private String[] getParamArray() throws KayuException {
        return super.splitUserParams(commandParams, COMMAND_WORD, Deadline.SPLIT_WORD);
    }
}

