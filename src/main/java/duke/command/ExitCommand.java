package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * ExitCommand class handles the 'bye' command to close Duke.
 */
public class ExitCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String closingMessage =  "Goodbye! Hope to see you again soon!\n";
        ui.printMessage(closingMessage);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ExitCommand;
    }
}
