package yoyo.command;

import yoyo.core.DialogHandler;
import yoyo.core.Storage;
import yoyo.exception.YoyoException;
import yoyo.task.Task;
import yoyo.task.TaskList;

/**
 * A command subclass representing "find" command.
 */
public class CommandFind extends Command {
    /**
     * Constructor for "find" command class.
     *
     * @param inputTokens Array of string tokens constructed from user input.
     */
    public CommandFind(String[] inputTokens) {
        super(inputTokens);
    }

    /**
     * Executes "find" command.
     *
     * @param tasks         Tasks currently in the Yoyo program.
     * @param storage       Storage instance of the Yoyo program.
     * @param dialogHandler Ui instance of Yoyo program.
     * @return The result string to be shown to user.
     * @throws YoyoException
     */
    @Override
    public String execute(TaskList tasks, Storage storage, DialogHandler dialogHandler)
            throws YoyoException {
        checkTwoTokenCommand(inputTokens);
        int taskListLength = tasks.size();
        if (taskListLength == 0) {
            return dialogHandler.printTaskList(tasks);
        }
        assert taskListLength != 0;

        String searchString = inputTokens[1];
        TaskList matchingTasks = new TaskList();

        for (int i = 0; i < taskListLength; i++) {
            Task currentTask = tasks.get(i);
            if (tasks.get(i).containsString(searchString)) {
                matchingTasks.add(currentTask);
            }
        }
        return dialogHandler.printMatchingTaskList(matchingTasks);
    }

}
