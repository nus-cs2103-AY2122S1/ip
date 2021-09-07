package command;

import duke.Storage;
import duke.Ui;
import task.TaskList;

/**
 * Command represents a command.
 *
 * @author Ho Wen Zhong
 */
public abstract class Command {

    /**
     * Executes the Command.
     *
     * @param tasks Current TaskList.
     * @param ui Ui object.
     * @param storage Storage object.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns whether the Command exits the program.
     *
     * @return Boolean indicating if Command exits program.
     */
    public abstract boolean isExit();
}
