package abyss.command;

import java.io.IOException;

import abyss.exception.AbyssException;

/**
 * Represents a general user command.
 */
public interface Command {
    /**
     * Executes the user command.
     *
     * @return Response from executing command.
     * @throws IOException If there is error writing to file.
     */
    String execute() throws IOException, AbyssException;
}
