package petal.command;

import petal.components.Responses;
import petal.components.Storage;
import petal.components.TaskList;
import petal.components.Ui;

/**
 * The UnintelligibleCommand implements Command and
 * deals with commands that cannot be made sense of
 */
public class UnintelligibleCommand implements Command {

    /**
     * Displays message to user informing them that the input was unintelligible
     *
     * @param taskList TaskList instance
     * @param ui Ui instance
     * @param storage Storage instance
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.output(Responses.UNINTELLIGIBLE);
        ui.output(Responses.REQUIRED_FORMAT);
    }
}
