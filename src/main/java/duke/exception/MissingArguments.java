package duke.exception;

import duke.command.Command;

/**
 * Throws exception when some expected arguments are missing.
 */
public class MissingArguments extends DukeException {

    public MissingArguments(Command.CommandTypes command) {
        super("Meow? There's missing arguments in your command " + command.toString() + "... Meow meow meow?");
    }

}
