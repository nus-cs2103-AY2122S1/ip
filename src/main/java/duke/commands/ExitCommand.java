package main.java.duke.commands;
import main.java.duke.*;

import java.io.IOException;

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

    public boolean isExit() {
        return true;
    }
}
