package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * ExitCommand class encapsulates command to exit Duke.
 */
public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, boolean shouldPrintMessage) {
        String message = "\"Bye. Hope to see you again soon!\"";
        if (shouldPrintMessage) {
            ui.showGoodBye();
        }
        return message;
    }
}
