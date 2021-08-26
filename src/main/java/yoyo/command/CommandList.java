package yoyo.command;

import yoyo.core.Storage;
import yoyo.core.Ui;
import yoyo.exception.YoyoException;
import yoyo.task.TaskList;

public class CommandList extends Command {
    public CommandList(String[] inputTokens) {
        super(inputTokens);
    }

    /**
     * Executes "list" command.
     *
     * @param tasks   Tasks currently in the Yoyo program.
     * @param storage Storage instance of the Yoyo program.
     * @param ui      Ui instance of Yoyo program.
     * @throws YoyoException
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        ui.printTaskList(tasks);
    }
}
