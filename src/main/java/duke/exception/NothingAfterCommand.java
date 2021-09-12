package duke.exception;

import duke.command.Commands;

/**
 * Throws exception when no arguments are passed after command.
 */
public class NothingAfterCommand extends DukeException {

    public NothingAfterCommand(Commands.CommandTypes command) {
        super("Meow? There's nothing after your command " + command.toString() + "... Meow meow meow?");
    }

}
