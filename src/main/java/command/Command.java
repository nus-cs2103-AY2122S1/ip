package command;

import java.time.format.DateTimeFormatter;

import bot.DukeException;
import bot.TaskList;
import bot.UserInterface;

/**
 * A class that encapsulates a general Command given to Duke.
 */
public abstract class Command {

    protected String input;
    protected DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy kkmm");

    /**
     * Constructor for the Command class.
     *
     * @param input The input given by the user.
     */
    public Command(String input) {
        this.input = input;
    }

    /**
     * Returns the proper response according to the given input.
     * This is an abstract method.
     *
     * @param list The list of tasks to be modified by the command.
     * @param ui The UI of Duke to be invoked by the command.
     * @return A String representation of Duke's response according to the input given by the user.
     * @throws DukeException if the input given is not of the correct format.
     */
    public abstract String execute(TaskList list, UserInterface ui) throws DukeException;

}
