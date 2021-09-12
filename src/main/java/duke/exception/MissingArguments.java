package duke.exception;

import duke.command.Commands;

/**
 * Throws exception when some expected arguments are missing.
 */
public class MissingArguments extends DukeException {

    public MissingArguments(Commands.CommandTypes command) {
        super("Meow? There's missing arguments in your command " + command.toString() + "... Meow meow meow?");
    }

}
