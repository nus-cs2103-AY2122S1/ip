package yoyo.command;

import yoyo.core.DialogHandler;
import yoyo.core.Storage;
import yoyo.exception.YoyoException;
import yoyo.task.Task;
import yoyo.task.TaskList;

/**
 * A command subclass representing "tag" command.
 */
public class CommandTag extends Command {
    /**
     * Constructor for "tag" command class.
     *
     * @param inputTokens Array of string tokens constructed from user input.
     */
    public CommandTag(String[] inputTokens) {
        super(inputTokens);
    }

    /**
     * Executes "tag" command.
     *
     * @param tasks         Tasks currently in the Yoyo program.
     * @param storage       Storage instance of the Yoyo program.
     * @param dialogHandler Ui instance of Yoyo program.
     * @return The result string to be shown to user.
     * @throws YoyoException
     */
    @Override
    public String execute(TaskList tasks, Storage storage, DialogHandler dialogHandler) throws YoyoException {

        try {
            String[] indexAndTags = inputTokens[1].split(" ", 2);
            checkTwoTokenCommand(indexAndTags);
            int taskIndex = Integer.parseInt(indexAndTags[0]) - 1;
            Task taskToTag = tasks.get(taskIndex);
            String[] tags = indexAndTags[1].split(" ");
            for (int i = 0; i < tags.length; i++) {
                taskToTag.addTag(tags[i]);
            }
            return dialogHandler.showAddTagMessage(taskToTag);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new YoyoException.YoyoTaskIndexException("Please enter A valid task index!");
        }
    }
}
