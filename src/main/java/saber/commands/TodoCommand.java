package saber.commands;

import saber.task.Todo;
import saber.tasklist.TaskList;
import saber.ui.TodoUI;

/**
 * Represents a Todo Command
 */
public class TodoCommand extends SaberCommand {
    private Todo todo;
    private boolean isMissingDescription;

    private TodoUI todoUI = new TodoUI();

    /**
     * Constructs  for TodoCommand
     *
     * @param task the description for the todo task to be added
     * @param isMissingDescription whether the todo task description is missing in the command
     */
    public TodoCommand(String task, boolean isMissingDescription) {
        Todo todo = new Todo(task, false);
        this.todo = todo;
        this.isMissingDescription = isMissingDescription;
    }

    /**
     * Executes the TodoCommand
     *
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
     * {@inheritdoc}
     */
    public String getResponse(TaskList taskList) {
        if (isMissingDescription) {
            return todoUI.getMissingDescriptionError();
        }
        taskList.add(todo);
        int totalTask = taskList.size();
        todoUI.setSuccessMessage(todo, totalTask);
        return todoUI.getSuccessMessage();
    }
}
