package main.java.duke.commands;

import java.io.IOException;

import main.java.duke.DukeException;
import main.java.duke.MainWindow;
import main.java.duke.Storage;
import main.java.duke.TaskList;

/**
 * A command that says bye to user.
 */
public class ExitCommand extends Command {

    /**
     * Constructs a new exit command with the given index.
     */
    public ExitCommand() {

    }

    /**
     * Executes the exit command.
     *
     * @param tasks given list of tasks
     * @param gui given ui object
     * @param storage given storage object
     */
    public String execute(TaskList tasks, MainWindow gui, Storage storage) throws IOException, DukeException {
        return ("Bye! Neko wishes to see you again soon!\n");
    }
}
