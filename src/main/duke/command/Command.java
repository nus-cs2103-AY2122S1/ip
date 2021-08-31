package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

abstract public class Command {

    /**
     * Execute user command
     * @param tasks List of tasks.
     * @param ui UI of Duke Chatbot.
     * @param storage Storage of Duke Chatbot.
     * @throws DukeException If execution fails.
     */
    abstract public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Check if user is ending the chatbot.
     * @return True if user is ending the chatbot.
     */
    abstract public boolean isExit();

}
