package main.java.duke.commands;

import main.java.duke.DukeException;
import main.java.duke.MainWindow;
import main.java.duke.Storage;
import main.java.duke.TaskList;

/**
 * An unknown command that will be responded with a sorry message.
 */
public class UnknownCommand extends Command {

    /**
     * Constructs a new unknown command.
     */
    public UnknownCommand() {

    }

    /**
     * Executes the unknown command.
     *
     * @param tasks given list of tasks
     * @param gui given gui object
     * @param storage given storage object
     * @throws DukeException duke exception with sorry message
     */
    public String execute(TaskList tasks, MainWindow gui, Storage storage) throws DukeException {
        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
