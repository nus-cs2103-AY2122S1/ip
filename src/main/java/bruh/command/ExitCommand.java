package bruh.command;

import bruh.storage.Storage;
import bruh.tasklist.TaskList;
import bruh.ui.Ui;

/**
 * Represents a command to exit the chatbot.
 */
public class ExitCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String runAndGenerateDescription(TaskList taskList, Ui ui, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }
}
