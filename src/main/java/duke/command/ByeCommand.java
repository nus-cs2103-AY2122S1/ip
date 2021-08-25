package duke.command;

import duke.TaskList;
import duke.Ui;

/**
 * Handles the bye command which exits duke.Duke.
 */
public class ByeCommand extends Command{
    public ByeCommand(String input) {
        super(input);
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui) {
        ui.goodbye();
        return true;
    }
}
