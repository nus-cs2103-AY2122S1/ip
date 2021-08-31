package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class ExitCommand extends Command {

    public ExitCommand(String fullCommand) {}

    /**
     * Execute user commands.
     * @param tasks List of tasks.
     * @param ui Ui of Duke chatbot.
     * @param storage Storage of Duke chatbot.
     * @throws DukeException If execution fails.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.print("Goodbye! Have a nice day!");
    }

    /**
     * Check if user is ending the chatbot.
     * @return True if user is ending the chatbot.
     */
    public boolean isExit() {
        return true;
    }

}
