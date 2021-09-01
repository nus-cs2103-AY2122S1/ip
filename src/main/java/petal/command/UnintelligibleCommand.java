package petal.command;

import petal.components.Responses;
import petal.components.Storage;
import petal.components.TaskList;

/**
 * The UnintelligibleCommand implements Command and
 * deals with commands that cannot be made sense of
 */
public class UnintelligibleCommand implements Command {

    /**
     * Displays message to user informing them that the input was unintelligible
     *
     * @param taskList TaskList instance
     * @param storage Storage instance
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return Responses.UNINTELLIGIBLE.toString();
    }
}
