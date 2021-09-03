package yoyo.command;

import yoyo.core.DialogHandler;
import yoyo.core.Storage;
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
     * @param dialogHandler Ui instance of Yoyo program.
     * @return The result string to be shown to user.
     * @throws YoyoException
     */
    @Override
    public String execute(TaskList tasks, Storage storage, DialogHandler dialogHandler) {
        return dialogHandler.printTaskList(tasks);
    }
}
