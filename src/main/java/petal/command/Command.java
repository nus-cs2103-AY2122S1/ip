package petal.command;

import petal.components.Storage;
import petal.components.TaskList;

/**
 * The Command interface represents all commands
 */
public interface Command {

    /**
     * Executes the given command
     *
     * @param taskList TaskList instance
     * @param storage Storage instance
     */
    String execute(TaskList taskList, Storage storage);

}
