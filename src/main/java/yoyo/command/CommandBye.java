package yoyo.command;

import yoyo.core.Storage;
import yoyo.core.Ui;
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
     * @param ui      Ui instance of Yoyo program.
     * @throws YoyoException
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        storage.deposit(tasks);
        ui.sayGoodbye();
    }

    @Override
    public boolean shouldContinueProgram() {
        return false;
    }
}
