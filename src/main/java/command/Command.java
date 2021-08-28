package main.java.command;

import java.time.format.DateTimeFormatter;
import main.java.bot.DukeException;
import main.java.bot.TaskList;
import main.java.bot.UserInterface;

public abstract class Command {

    protected String input;

    protected DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy kkmm");

    public Command(String input) {
        this.input = input;
    }

    /**
     * An abstract function that creates and returns the reply according to the user input.
     *
     * @return A response corresponding to the user input / main.java.command.
     */
    public abstract void execute(TaskList list, UserInterface ui) throws DukeException;

}