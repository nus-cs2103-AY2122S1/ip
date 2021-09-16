package jwbot.command;

import jwbot.data.TaskList;
import jwbot.data.exception.JwBotException;
import jwbot.data.task.Task;
import jwbot.storage.Storage;
import jwbot.ui.Ui;

/**
 * the abstract class command that is a skeleton for all types of user command.
 *
 * @author  Yim Jaewon
 */
public abstract class Command {

    protected String input;

    /**
     * The constructor of the Command class.
     *
     * @param input the command entered by the user
     */
    public Command(String input) {
        this.input = input;
    }

    /**
     * The method that executes the command
     *
     * @param tasks list of the tasks
     * @param ui the ui object responsible for user interaction
     * @param storage the storage object responsible for writing and reading txt file
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws JwBotException;

    protected abstract Task processTask(TaskList tasks);

    /**
     * The method that checks if the bot should be stopped.
     *
     * @return the boolean to decide whether to stop the bot
     */
    public abstract boolean isExit();
}
