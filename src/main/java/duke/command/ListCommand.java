package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * ListCommand class encapsulates command to list tasks.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, boolean shouldPrintMessage) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1 + ". " + tasks.get(i) + "\n");
        }
        String message = sb.toString();
        if (shouldPrintMessage) {
            ui.showMessage(message);
        }
        return message;
    }
}
