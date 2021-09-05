package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {

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
     * @throws DukeException If execution fails.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    /**
     * Check if user is ending the chatbot.
     * @return True if user is ending the chatbot.
     */
    public boolean isExit() {
        return true;
    }

}
