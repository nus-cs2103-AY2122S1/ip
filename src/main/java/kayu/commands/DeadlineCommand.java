package kayu.commands;

import static kayu.commands.CommandMessage.MESSAGE_CREATED_DEADLINE;
import static kayu.commands.CommandType.DEADLINE;

import java.time.LocalDate;
import java.time.LocalTime;

import kayu.exception.DukeException;
import kayu.parser.DateTimeFormat;
import kayu.service.TaskList;
import kayu.task.Deadline;
import kayu.task.Task;

/**
 * Represents an {@link kayu.commands.AddTaskCommand} that creates a {@link kayu.task.Deadline}
 * and saves it in {@link kayu.service.TaskList}.
 */
public class DeadlineCommand extends AddTaskCommand {

    /** Key word for command. */
    public static final String COMMAND_WORD = "deadline";

    /**
     * Initializes a Deadline- {@link kayu.commands.AddTaskCommand}.
     *
     * @param commandParams String parameters fed into the command by user.
     * @param dateTimeFormat {@link kayu.parser.DateTimeFormat} used in parsing, if required.
     */
    public DeadlineCommand(String commandParams, DateTimeFormat dateTimeFormat) {
        super(DEADLINE, commandParams, dateTimeFormat);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList) throws DukeException {
        String[] paramArray = super.splitUserParams(commandParams, COMMAND_WORD, Deadline.SPLIT_WORD);
        
        String desc = super.extractDesc(paramArray, COMMAND_WORD);
        LocalDate byDate = super.extractDate(paramArray);
        LocalTime byTime = super.extractTime(paramArray);

        Task deadline = new Deadline(desc, byDate, byTime);
        taskList.addTask(deadline);
        
        return String.format(MESSAGE_CREATED_DEADLINE, deadline, taskList.getCapacity());
    }
}

