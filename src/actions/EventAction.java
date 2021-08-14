package actions;

import components.Event;
import components.TaskList;

public class EventAction extends Action {
    AppState applicationState;
    String todo;
    String eventAt;

    public EventAction(AppState applicationState, String todo, String eventAt) {
        this.applicationState = applicationState;
        this.todo = todo;
        this.eventAt = eventAt;
    }

    public AppState run() {
        TaskList taskList = this.applicationState.taskList;
        TaskList newTaskList = taskList.addTask(new Event(this.todo, false, this.eventAt));
        System.out.println(String.format("Excellent! I've added this event: \n%s",
                taskList.showTask(taskList.size() - 1)));
        return new AppState(applicationState.userExit, newTaskList);
    }
}
