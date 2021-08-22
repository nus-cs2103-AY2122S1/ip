package duke.command;

import duke.exception.DukeException;
import duke.exception.NoSuchCommandException;

public class UnrecognisedCommand extends Command {
    private String input;

    public UnrecognisedCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute() throws DukeException {
        throw new NoSuchCommandException(this.input.split(" ", 2)[0]);
    }
}
