package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Adds a task to the user's list of tasks.
 */
public abstract class AddCommand implements Command {
    protected static final String DATE_DELIMITER = " /";
    protected Task task;

    @Override
    public String execute(TaskList tasks, Ui ui) {
        tasks.add(task);
        int numTasksRemaining = tasks.numTasks();
        return ui.showTaskAddedMessage(task, numTasksRemaining);
    }

    protected static String getTaskDescription(String userInput) throws
            ArrayIndexOutOfBoundsException {
        String[] commandParamsSplit = userInput.split(DATE_DELIMITER, 2);
        String taskDescription = commandParamsSplit[0];
        return taskDescription;
    }
}
