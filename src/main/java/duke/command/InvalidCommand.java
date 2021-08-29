package duke.command;

import duke.exception.InvalidCommandException;

/**
 * Class that represents the an invalid Command that is not recognised.
 *
 * @author Benedict Chua
 */
public class InvalidCommand extends Command {
    private String input;

    public InvalidCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute() {
        throw new InvalidCommandException(this.input);
    }
}
