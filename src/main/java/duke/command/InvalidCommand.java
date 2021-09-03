package duke.command;

import duke.exception.InvalidCommandException;

/**
 * Class that represents the an invalid Command that is not recognised.
 *
 * @author Benedict Chua
 */
public class InvalidCommand extends Command {
    private String input;

    /**
     * Constructor for InvalidCommand.
     *
     * @param input String of the command inputted by the user.
     */
    public InvalidCommand(String input) {
        this.input = input;

        String command = input.split(" ")[0];
        assert !command.matches("bye|list|check|find|done|todo|deadline|event|delete")
            : "Command is wrongly categorised as invalid";
    }

    /**
     * {@inheritDoc}
     *
     * This throws an invalid command exception.
     */
    @Override
    public String execute() {
        throw new InvalidCommandException(this.input);
    }
}
