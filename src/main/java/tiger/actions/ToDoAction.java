package tiger.actions;

import tiger.app.AppState;
import tiger.components.TaskList;
import tiger.components.ToDo;
import tiger.constants.Priority;

/**
 * Represents the action of the user adding a new Todo.
 */

public class ToDoAction extends Action {

    private AppState applicationState;
    private String todo;
    private Priority priority;

    /**
     * Constructor for the {@code ToDoAction} class.
     *
     * @param applicationState Context of application from which to run the task from.
     * @param todo Description of the user's task.
     * @param priority The priority of the task, specified by the user (if any).
     */

    public ToDoAction(AppState applicationState, String todo, Priority priority) {
        this.applicationState = applicationState;
        this.todo = todo;
        this.priority = priority;
    }

    /**
     * Runs the {@code ToDo} action.
     *
     * @return an updated {@code AppState}.
     */

    public AppState run() {
        TaskList taskList = this.applicationState.getTaskList();
        TaskList newTaskList = taskList.addTask(new ToDo(this.todo, false, priority));
        String response = String.format("Excellent! I've added this task:\n%s",
                taskList.showTask(taskList.size() - 1));
        return new AppState(newTaskList, response);
    }



}
