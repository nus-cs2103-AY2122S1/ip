package main.java;


public class CommandException extends DukeException {

    /**
     * Constructor to create a new CommandException.
     * @param task task to be added.
     * @param command command required.
     */
    public CommandException(String task, String command) {
        super(String.format("Your task of %s requires the command %s", task, command));
    }
}
