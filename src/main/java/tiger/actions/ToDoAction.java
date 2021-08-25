package tiger.actions;

import tiger.app.AppState;
import tiger.components.TaskList;
import tiger.components.ToDo;
import tiger.constants.Priority;

public class ToDoAction extends Action {

    private AppState applicationState;
    private String todo;
    private Priority priority;

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
        TaskList taskList = this.applicationState.taskList;
        TaskList newTaskList = taskList.addTask(new ToDo(this.todo, false, priority));
        String response = String.format("Excellent! I've added this task:\n%s",
                taskList.showTask(taskList.size() - 1));
        return new AppState(false, newTaskList, response);
    }



}
