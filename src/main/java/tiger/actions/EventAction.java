package tiger.actions;

import tiger.components.Event;
import tiger.components.TaskList;

public class EventAction extends Action {
    AppState applicationState;
    String todo;
    String eventAt;

    public EventAction(AppState applicationState, String todo, String eventAt) {
        this.applicationState = applicationState;
        this.todo = todo;
        this.eventAt = eventAt;
    }

    /**
     * Runs the {@code Event} action.
     *
     * @return an updated {@code AppState}.
     */

    public AppState run() {
        TaskList taskList = this.applicationState.taskList;
        TaskList newTaskList = taskList.addTask(Event.of(this.todo, false, this.eventAt));
        System.out.println(String.format("Excellent! I've added this event:\n%s",
                taskList.showTask(taskList.size() - 1)));
        return new AppState(applicationState.userExit, newTaskList);
    }
}
