package eightbit.command;

import eightbit.EightBitException;
import eightbit.util.Storage;
import eightbit.util.TaskList;

/**
 * Represents a user command. Contains implementation of the execution of the user command.
 */
public abstract class Command {

    /**
     * Executes the command depending on their implementations.
     *
     * @param taskList User's list of tasks.
     * @param storage  Storage responsible for reading/writing the file.
     * @return The response after executing the command.
     * @throws EightBitException If user input is invalid or exceptions are thrown depending on the Command.
     */
    public abstract String execute(TaskList taskList, Storage storage) throws EightBitException;
}
