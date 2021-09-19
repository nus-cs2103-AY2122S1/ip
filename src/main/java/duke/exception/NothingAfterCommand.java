package duke.exception;

import duke.command.Command;

/**
 * Throws exception when no arguments are passed after command.
 */
public class NothingAfterCommand extends DukeException {

    public NothingAfterCommand(Command.CommandTypes command) {
        super("Meow? There's nothing after your command " + command.toString() + "... Meow meow meow?");
    }

}
