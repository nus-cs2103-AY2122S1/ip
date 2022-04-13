package duke.commands;

import duke.DukeException;

public class UnknownCommandException extends DukeException {
    public UnknownCommandException() {
        super("Me no understanderino");
    }
}
