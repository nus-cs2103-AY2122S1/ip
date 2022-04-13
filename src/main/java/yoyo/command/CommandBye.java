package yoyo.command;

import yoyo.core.DialogHandler;
import yoyo.core.Storage;
import yoyo.exception.YoyoException;
import yoyo.task.TaskList;

/**
 * A command subclass representing "bye" command.
 */
public class CommandBye extends Command {

    /**
     * Constructor for "bye" command class.
     *
     * @param inputTokens Array of string tokens constructed from user input.
     */
    public CommandBye(String[] inputTokens) {
        super(inputTokens);
    }

    /**
     * Executes "bye" command.
     *
     * @param tasks         Tasks currently in the Yoyo program.
     * @param storage       Storage instance of the Yoyo program.
     * @param dialogHandler Ui instance of Yoyo program.
     * @return The result string to be shown to user.
     * @throws YoyoException
     */
    @Override
    public String execute(TaskList tasks, Storage storage, DialogHandler dialogHandler) {
        storage.depositTask(tasks);
        return dialogHandler.sayGoodbye();
    }

}
