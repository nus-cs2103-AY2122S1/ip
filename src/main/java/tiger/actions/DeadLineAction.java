package tiger.actions;

import tiger.app.AppState;
import tiger.components.DeadLine;
import tiger.components.TaskList;
import tiger.constants.Priority;
import tiger.utils.CustomDate;

public class DeadLineAction extends Action {
    private AppState applicationState;
    private String todo;
    private CustomDate date;
    private Priority priority;

    public DeadLineAction(AppState applicationState, String todo, CustomDate date, Priority priority) {
        this.applicationState = applicationState;
        this.todo = todo;
        this.date = date;
        this.priority = priority;
    }

    /**
     * Runs the {@code DeadLine} action.
     *
     * @return an updated {@code AppState}.
     */

    public AppState run() {
        TaskList taskList = this.applicationState.taskList;
        TaskList newTaskList = taskList.addTask(new DeadLine(this.todo, false, this.date, this.priority));
        String response = String.format("Excellent! I've added this deadline:\n%s",
                taskList.showTask(taskList.size() - 1));
        return new AppState(false, newTaskList, response);
    }
}
