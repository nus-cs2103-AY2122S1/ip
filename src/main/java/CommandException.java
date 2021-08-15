package main.java;


public class CommandException extends DukeException {

    /**
     *  Constructor to create a new CommandException
     * @param task the task that the user wants to add to the list
     * @param command the subsequent /command that the user wants to do
     */
    public CommandException(String task, String command) {
        super(String.format("Your task of %s requires the command %s", task, command));
    }
}
