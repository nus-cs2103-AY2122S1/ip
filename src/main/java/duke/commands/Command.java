package duke.commands;

import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * Forms the basis for Duke Commands classes. All the command classes extend from this abstract
 * Command class. The sub-classes to represent various user commands to the chat-bot.
 */
public abstract class Command {

    /**
     * Executes this command accordingly. The actual implementation must be defined by
     * the subclass.
     *
     * @param tasks the full task list containing all the tasks.
     * @param ui the ui instance.
     * @param storage the storage instance.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
