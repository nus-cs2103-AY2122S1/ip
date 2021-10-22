package duke.command;

import duke.DukeException;
import duke.ResponsePair;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A Command class representing the 'Delete' command.
 */
public class ExitCommand implements Command {

    /**
     * Create a new Command indicating exit from Duke chatbot.
     * @param fullCommand Unedited user command.
     */
    public ExitCommand(String fullCommand) {}

    /**
     * Execute user command.
     * @param tasks List of tasks.
     * @param ui UI of Duke Chatbot.
     * @param storage Storage of Duke Chatbot.
     * @return String of Duke chatbot response.
     * @throws DukeException If execution fails.
     */
    public ResponsePair execute(TaskList tasks, Ui ui, Storage storage) {
        return new ResponsePair(ui.showGoodbye(), isExit());
    }

    /**
     * Check if user is ending the chatbot.
     * @return True if user is ending the chatbot.
     */
    public boolean isExit() {
        return true;
    }

}
