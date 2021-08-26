package yoyo.command;

import yoyo.core.Storage;
import yoyo.core.Ui;
import yoyo.exception.YoyoException;
import yoyo.task.Task;
import yoyo.task.TaskList;

public class CommandFind extends Command {
    public CommandFind(String[] inputTokens) {
        super(inputTokens);
    }

    /**
     * Executes "find" command.
     *
     * @param tasks   Tasks currently in the Yoyo program.
     * @param storage Storage instance of the Yoyo program.
     * @param ui      Ui instance of Yoyo program.
     * @throws YoyoException
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui)
            throws YoyoException {
        checkCompleteCommand(inputTokens);
        int taskListLength = tasks.size();
        String searchString = inputTokens[1];
        TaskList matchingTasks = new TaskList();
        for (int i = 0; i < taskListLength; i++) {
            Task currentTask = tasks.get(i);
            if (tasks.get(i).containsString(searchString)) {
                matchingTasks.add(currentTask);
            }
        }
        if (taskListLength == 0) {
            ui.printTaskList(tasks);
        } else {
            ui.printMatchingTaskList(matchingTasks);
        }

    }

}
