package duke.command;

import duke.*;

/**
 * An interface to encapsulate all Command types.
 */
public interface Command {

    /**
     * Execute user command
     * @param tasks List of tasks.
     * @param ui UI of Duke Chatbot.
     * @param storage Storage of Duke Chatbot.
     * @throws DukeException If execution fails.
     */
    ResponsePair execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Check if user is ending the chatbot.
     * @return True if user is ending the chatbot.
     */
    boolean isExit();

}
