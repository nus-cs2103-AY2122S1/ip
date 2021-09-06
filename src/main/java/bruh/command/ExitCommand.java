package bruh.command;

import bruh.storage.Storage;
import bruh.tasklist.TaskList;
import bruh.ui.Ui;

/**
 * Represents a command to exit the chatbot.
 */
public class ExitCommand extends Command {
    private static final String DESCRIPTION = "Bye. Hope to see you again soon!";

    @Override
    public String runAndGenerateDescription(TaskList taskList, Ui ui, Storage storage) {
        return DESCRIPTION;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
