package main.java.command;

import main.java.bot.DukeException;
import main.java.bot.TaskList;
import main.java.bot.UserInterface;

/**
 * A class that encapsulates an Exit Command given to Duke.
 */
public class ExitCommand extends Command {

    /**
     * Constructor for the ExitCommand class.
     *
     * @param input The input given by the user.
     */
    public ExitCommand(String input) {
        super(input);
    }

    /**
     * Executes the exit command with the given input.
     *
     * @param list The list of tasks to be modified by the command.
     * @param ui The UI of Duke to be invoked by the command.
     * @throws DukeException if the input given is not of the correct format.
     */
    public void execute(TaskList list, UserInterface ui) {
        ui.showBye();
    }
}
