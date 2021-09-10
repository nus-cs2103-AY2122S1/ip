package command;

import bot.Bot;
import bot.DukeException;
import bot.TaskList;
import bot.UserInterface;

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
     * Returns the proper response according to the given input.
     *
     * @param list The list of tasks to be modified by the command.
     * @param ui The UI of Duke to be invoked by the command.
     * @return A String representation of Duke's response according to the input given by the user.
     * @throws DukeException if the input given is not of the correct format.
     */
    @Override
    public String execute(TaskList list, UserInterface ui) {
        return ui.showExitMessage();
    }
}
