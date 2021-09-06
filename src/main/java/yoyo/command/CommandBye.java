package yoyo.command;

import yoyo.core.DialogHandler;
import yoyo.core.Storage;
import yoyo.exception.YoyoException;
import yoyo.task.TaskList;

public class CommandBye extends Command {

    public CommandBye(String[] inputTokens) {
        super(inputTokens);
    }

    /**
     * Executes "bye" command.
     *
     * @param tasks   Tasks currently in the Yoyo program.
     * @param storage Storage instance of the Yoyo program.
     * @param dialogHandler      Ui instance of Yoyo program.
     * @return The result string to be shown to user.
     * @throws YoyoException
     */
    @Override
    public String execute(TaskList tasks, Storage storage, DialogHandler dialogHandler) {
        storage.deposit(tasks);
        return dialogHandler.sayGoodbye();
    }

}
