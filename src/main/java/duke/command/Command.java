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

    /**
     * Checks if the command is the exit command
     *
     * @return true if command is exit command, else false
     */
    boolean isExit();
}
