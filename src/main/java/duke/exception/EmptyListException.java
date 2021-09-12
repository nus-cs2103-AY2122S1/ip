package duke.exception;

import duke.command.Commands;

/**
 * Throws exception when list is empty.
 */
public class EmptyListException extends DukeException {

    public EmptyListException(Commands.CommandTypes command) {
        super("You have nothing in your list to " + command.toString() + ", meow!");
    }

}
