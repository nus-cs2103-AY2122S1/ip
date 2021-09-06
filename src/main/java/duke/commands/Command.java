package main.java.duke.commands;
import java.io.IOException;

import main.java.duke.*;

/**
 * A command class.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks given list of tasks
     * @param gui given main window object
     * @param storage given storage object
     * @throws IOException input and output exception
     * @throws DukeException duke exception
     */
    public abstract String execute(TaskList tasks, MainWindow gui, Storage storage) throws IOException, DukeException;
}
