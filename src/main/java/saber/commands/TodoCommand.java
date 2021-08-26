package saber.commands;

import saber.tasklist.TaskList;
import saber.task.Todo;
import saber.ui.TodoUI;

/**
 * A class to represent a Todo Command
 */
public class TodoCommand extends SaberCommand {
    private Todo todo;
    private boolean isMissingDescription;

    private TodoUI todoUI = new TodoUI();

    /**
     * A constructor for TodoCommand
     * @param task the description for the todo task to be added
     * @param isMissingDescription whether the todo task description is missing in the command
     */
    public TodoCommand(String task, boolean isMissingDescription) {
        Todo todo = new Todo(task, false);
        this.todo = todo;
        this.isMissingDescription = isMissingDescription;
    }

    /**
     * A function to execute the TodoCommand
     * @param taskList the TaskList to which the newly created todo task is added to
     */
    public void execute(TaskList taskList) {
        if (isMissingDescription) {
            todoUI.showMissingDescriptionError();
            return;
        }
        taskList.add(todo);
        int totalTask = taskList.size();
        todoUI.setSuccessMessage(todo, totalTask);
        todoUI.showSuccess();
    }

    /**
     * A function to determine whether the current command is a terminating command (a ByeCommand)
     * @return false
     */
    public boolean isExit() {
        return false;
    }
}
