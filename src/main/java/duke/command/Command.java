package duke.command;

import duke.DukeException;

/**
 * Interface for commands
 */
public interface Command {
    /**
     * Runs the command
     *
     * @return message if success
     * @throws DukeException if cannot access memory
     */
    String run() throws DukeException;

    boolean isExit();
}
