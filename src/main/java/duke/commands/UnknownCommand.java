package main.java.duke.commands;
import java.io.IOException;

import main.java.duke.*;

public class UnknownCommand extends Command {

    /**
     * Constructs a new unknown command.
     */
    public UnknownCommand() {

    }

    /**
     * Executes the unknown command.
     * @param tasks given list of tasks
     * @param gui given gui object
     * @param storage given storage object
     * @throws IOException
     * @throws DukeException
     */
    public String execute(TaskList tasks, MainWindow gui, Storage storage) throws DukeException {
        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    };

    public boolean isExit() {
        return false;
    }
}
