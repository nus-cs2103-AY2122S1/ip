package main.java.command;

import java.time.format.DateTimeFormatter;
import main.java.bot.DukeException;
import main.java.bot.TaskList;
import main.java.bot.UserInterface;

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
     * Executes the command with the given input.
     * This is an abstract method.
     *
     * @param list The list of tasks to be modified by the command.
     * @param ui The UI of Duke to be invoked by the command.
     * @throws DukeException if the input given is not of the correct format.
     */
    public abstract void execute(TaskList list, UserInterface ui) throws DukeException;

}