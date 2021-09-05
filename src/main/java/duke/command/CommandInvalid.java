package duke.command;

import duke.Ui;

/**
 * Invalid command
 */
public class CommandInvalid extends Command {

    private final String input;

    /**
     * Constructor
     *
     * @param input Input that caused the error to occur
     */
    public CommandInvalid(String input) {
        this.description = "";
        this.commandName = "";
        this.arguments = new String[]{};
        this.input = input;
    }

    /**
     * Displays error message
     */
    @Override
    public String execute() {
        return "eeeeeee~dameda!!\n" + input + " isn't a valid command!";
    }
}
