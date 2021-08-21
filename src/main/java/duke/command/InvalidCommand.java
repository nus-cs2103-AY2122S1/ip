package duke.command;

import duke.exception.InvalidCommandException;

public class InvalidCommand extends Command {
    private String input;

    public InvalidCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute() {
        throw new InvalidCommandException(this.input);
    }
}