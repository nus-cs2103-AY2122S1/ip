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
     * Display error message
     */
    @Override
    public void execute() {
        System.out.println(Ui.OUTPUT_DISPLAY + "☹ eeeeeee~dameda!! " + input + " isn't a valid command!");
    }
}
