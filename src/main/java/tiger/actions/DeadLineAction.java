package tiger.actions;

import tiger.app.AppState;
import tiger.components.DeadLine;
import tiger.components.TaskList;
import tiger.utils.CustomDate;

public class DeadLineAction extends Action {
    private AppState applicationState;
    private String todo;
    private CustomDate date;

    public DeadLineAction(AppState applicationState, String todo, CustomDate date) {
        this.applicationState = applicationState;
        this.todo = todo;
        this.date = date;
    }

    /**
     * Runs the {@code DeadLine} action.
     *
     * @return an updated {@code AppState}.
     */

    public AppState run() {
        TaskList taskList = this.applicationState.taskList;
        TaskList newTaskList = taskList.addTask(new DeadLine(this.todo, false, this.date));
        String response = String.format("Excellent! I've added this deadline:\n%s",
                taskList.showTask(taskList.size() - 1));
        return new AppState(false, newTaskList, response);
    }
}
