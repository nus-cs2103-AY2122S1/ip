package duke.command;

import duke.exception.DukeException;

/**
 * Represents a general command.
 */
public interface Command {

    /* List of all command types */
    enum CommandTypes {
        LIST,
        DONE,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        SAVE,
        BYE,
        FIND,
        LOAD
    }

    /**
     * Runs the command.
     *
     * @return String to print
     * @throws DukeException
     */
    String run() throws DukeException;
}
