package tiger.actions;

import tiger.app.AppState;
import tiger.components.Event;
import tiger.components.TaskList;
import tiger.constants.Priority;
import tiger.utils.CustomDate;

/**
 * Represents the action of the user adding an event.
 */

public class EventAction extends Action {

    private AppState applicationState;
    private String todo;
    private CustomDate date;
    private Priority priority;

    /**
     * Constructor for the {@code EventAction class}
     *
     * @param applicationState Context of application from which to run the task from.
     * @param todo Description of the users event.
     * @param date Deadline of the task.
     * @param priority The priority of the task, specified by the user (if any).
     */

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
        TaskList taskList = this.applicationState.getTaskList();
        TaskList newTaskList = taskList.addTask(new Event(this.todo, false, this.date, this.priority));
        String response = String.format("Excellent! I've added this event:\n%s",
                taskList.showTask(taskList.size() - 1));
        return new AppState(newTaskList, response);
    }
}
