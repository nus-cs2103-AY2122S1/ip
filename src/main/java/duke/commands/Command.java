package duke.commands;

import duke.ItemList;
import duke.DukeException;
import duke.Ui;

/**
 * Represents functionalities of commands that a <code>Duke</code> object can
 * interpret and execute.
 */
public abstract class Command {
    /**
     * Executes the command by altering the inputs.
     * 
     * @param itemList List to update.
     * @param ui UI to update.
     * @throws DukeException If errors occur.
     */
    public abstract void execute(ItemList itemList, Ui ui) throws DukeException;

    /**
     * Reads the command arguments which spawned the object itself.
     * Class members are updated and buffered with arguments that will
     * apply when <code>Command.execute</code> is later called.
     * 
     * @param line Line which contains user input arguments.
     * @throws DukeException If input is invalid at runtime.
     */
    public abstract void parseLine(String line) throws DukeException;

    /**
     * Returns a flag to notify the <code>Duke</code> object to terminate
     * its runtime.
     * @return Whether the command represents a termination.
     */
    public boolean isExit() {
        return false;
    }
}
