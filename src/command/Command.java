package command;

import bot.DukeException;
import bot.TaskList;
import bot.UserInterface;

import java.time.format.DateTimeFormatter;

public abstract class Command {

    protected String input;

    protected DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy kkmm");

    public Command(String input) {
        this.input = input;
    }

    /**
     * An abstract function that creates and returns the reply according to the user input.
     *
     * @return A response corresponding to the user input / command.
     */
    public abstract void execute(TaskList list, UserInterface ui) throws DukeException;

}