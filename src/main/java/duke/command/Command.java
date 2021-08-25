package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

public abstract class Command {
    /**
     * Execute the command
     *
     * @param tasks    the TaskList
     * @param ui       the Ui
     * @param storage  the data source
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Check if the command is an ExitCommand
     *
     * @return           boolean stating if command is ExitCommand
     */
    public abstract boolean isExit();
}
