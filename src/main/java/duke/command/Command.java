package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * An interface for Command. An interface that will convert all valid user inputs to executable
 * commands.
 */
public interface Command {

    /**
     * Method to determine if the program should continue running.
     *
     * @return A boolean value. True means the program continues.
     */
    boolean isRunning();

    /**
     * Method to execute the given command.
     *
     * @param t The TaskList loaded.
     * @param ui The Ui object running the program.
     * @param storage The Storage object handling the loading and saving.
     * @throws DukeException Thrown in the event of input format errors.
     */
    String execute(TaskList t, Ui ui, Storage storage) throws DukeException;
}
