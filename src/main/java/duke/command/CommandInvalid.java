package duke.command;

import duke.Ui;

public class CommandInvalid extends Command {

    private final String input;

    public CommandInvalid(String input) {
        this.description = "";
        this.commandName = "";
        this.input = input;
    }

    @Override
    public void execute() {
        System.out.println(Ui.OUTPUT_DISPLAY + "☹ eeeeeee~dameda!! " + input + " isn't a valid command!");
    }
}
