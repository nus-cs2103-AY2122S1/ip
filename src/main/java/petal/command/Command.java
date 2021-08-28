package petal.command;

import petal.components.Storage;
import petal.components.TaskList;
import petal.components.Ui;

/**
 * The Command interface represents all Command
 */
public interface Command {

    /**
     * Executes the given command
     *
     * @param taskList TaskList instance
     * @param ui Ui instance
     * @param storage Storage instance
     */
    void execute(TaskList taskList, Ui ui, Storage storage);

}
