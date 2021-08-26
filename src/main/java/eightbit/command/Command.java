package eightbit.command;

import eightbit.EightBitException;
import eightbit.util.Storage;
import eightbit.util.TaskList;
import eightbit.util.Ui;

/**
 * Represents a user command. Contains implementation of the execution of the user command.
 */
public abstract class Command {

    protected boolean isExit = false;

    /**
     * Returns true if the user enters "bye" to exit the program.
     *
     * @return true if user enters "bye", false otherwise.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Executes the command depending on their implementations.
     *
     * @param taskList User's list of tasks.
     * @param ui Ui responsible for printing messages.
     * @param storage Storage responsible for reading/writing the file.
     * @throws EightBitException If user input is invalid or exceptions are thrown depending on the Command.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws EightBitException;
}
