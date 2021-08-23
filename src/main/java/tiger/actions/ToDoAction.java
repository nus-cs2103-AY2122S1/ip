package tiger.actions;

import tiger.app.AppState;
import tiger.components.TaskList;
import tiger.components.ToDo;

public class ToDoAction extends Action {
    AppState applicationState;
    String todo;

    public ToDoAction(AppState applicationState, String todo) {
        this.applicationState = applicationState;
        this.todo = todo;
    }

    /**
     * Runs the {@code ToDo} action.
     *
     * @return an updated {@code AppState}.
     */

    public AppState run() {
        TaskList taskList = this.applicationState.taskList;
        TaskList newTaskList = taskList.addTask(ToDo.of(this.todo, false));
        String response = String.format("Excellent! I've added this task:\n%s",
                taskList.showTask(taskList.size() - 1));
        return new AppState(false, newTaskList, response);
    }



}
