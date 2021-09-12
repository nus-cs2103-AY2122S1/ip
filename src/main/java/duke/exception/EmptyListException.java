package duke.exception;

import duke.command.Command;

/**
 * Throws exception when list is empty.
 */
public class EmptyListException extends DukeException {

    public EmptyListException(Command.CommandTypes command) {
        super("You have nothing in your list to " + command.toString() + ", meow!");
    }

}
