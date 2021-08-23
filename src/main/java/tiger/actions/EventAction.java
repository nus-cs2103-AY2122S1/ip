package tiger.actions;

import tiger.components.Event;
import tiger.components.TaskList;
import tiger.utils.CustomDate;

public class EventAction extends Action {
    AppState applicationState;
    String todo;
    CustomDate date;

    public EventAction(AppState applicationState, String todo, CustomDate date) {
        this.applicationState = applicationState;
        this.todo = todo;
        this.date = date;
    }

    /**
     * Runs the {@code Event} action.
     *
     * @return an updated {@code AppState}.
     */

    public AppState run() {
        TaskList taskList = this.applicationState.taskList;
        TaskList newTaskList = taskList.addTask(new Event(this.todo, false, this.date));
        System.out.println(String.format("Excellent! I've added this event:\n%s",
                taskList.showTask(taskList.size() - 1)));
        return new AppState(applicationState.userExit, newTaskList);
    }
}
