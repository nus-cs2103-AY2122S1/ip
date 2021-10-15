package me.yukun99.ip.commands;

import java.util.Arrays;
import java.util.Objects;

import me.yukun99.ip.core.TaskList;
import me.yukun99.ip.exceptions.HelpBotDateTimeFormatException;
import me.yukun99.ip.exceptions.HelpBotIllegalArgumentException;
import me.yukun99.ip.exceptions.HelpBotInvalidTaskTypeException;
import me.yukun99.ip.exceptions.HelpBotIoException;

/**
 * Commands sent to the HelpBot.
 */
public abstract class Command {
    // arguments of the command sent by the user
    protected final String[] args;
    // TaskList instance from the current HelpBot instance.
    protected final TaskList taskList;

    /**
     * Constructor for a Command instance.
     *
     * @param args Arguments of the command sent by the user.
     * @param taskList TaskList instance from the current HelpBot instance.
     */
    public Command(String[] args, TaskList taskList) {
        this.args = args;
        this.taskList = taskList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Command command = (Command) o;
        return Arrays.equals(args, command.args) && Objects.equals(taskList, command.taskList);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(taskList);
        result = 31 * result + Arrays.hashCode(args);
        return result;
    }

    /**
     * Runs the command, and returns the response.
     *
     * @throws HelpBotInvalidTaskTypeException If user tries to edit the time of a ToDo task.
     * @throws HelpBotIllegalArgumentException If user specified arguments are invalid or missing.
     * @throws HelpBotDateTimeFormatException If user specifies the wrong date/time format.
     * @throws HelpBotIoException If save/output files could not be accessed.
     */
    public abstract String getResponse()
            throws HelpBotInvalidTaskTypeException, HelpBotIllegalArgumentException, HelpBotDateTimeFormatException,
            HelpBotIoException;
}
