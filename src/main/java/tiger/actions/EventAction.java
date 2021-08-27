package tiger.actions;

import tiger.app.AppState;
import tiger.components.Event;
import tiger.components.TaskList;
import tiger.constants.Priority;
import tiger.utils.CustomDate;

public class EventAction extends Action {
    AppState applicationState;
    String todo;
    CustomDate date;
    Priority priority;

    public EventAction(AppState applicationState, String todo, CustomDate date, Priority priority) {
        this.applicationState = applicationState;
        this.todo = todo;
        this.date = date;
        this.priority = priority;
    }

    /**
     * Runs the {@code Event} action.
     *
     * @return an updated {@code AppState}.
     */

    public AppState run() {
        TaskList taskList = this.applicationState.taskList;
        TaskList newTaskList = taskList.addTask(new Event(this.todo, false, this.date, this.priority));
        String response = String.format("Excellent! I've added this event:\n%s",
                taskList.showTask(taskList.size() - 1));
        return new AppState(false, newTaskList, response);
    }
}
