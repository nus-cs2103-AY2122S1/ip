package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * ExitCommand class handles the 'bye' command to close Duke.
 */
public class ExitCommand extends Command {

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return "Goodbye! Hope to see you again soon!\n";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ExitCommand;
    }
}
