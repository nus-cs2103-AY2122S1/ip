package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.DukeException;;

abstract public class Command {

    /**
     * Execute user commands.
     * @param tasks List of tasks.
     * @param ui Ui of Duke chatbot.
     * @param storage Storage of Duke chatbot.
     * @throws DukeException If execution fails.
     */
    abstract public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Check if user is ending the chatbot.
     * @return True if user is ending the chatbot.
     */
    abstract public boolean isExit();

}
