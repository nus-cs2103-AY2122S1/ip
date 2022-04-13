package tiger.actions;

import tiger.app.AppState;
import tiger.components.DeadLine;
import tiger.components.TaskList;
import tiger.constants.Priority;
import tiger.utils.CustomDate;

/**
 * Represents the action of the user adding a deadline.
 */

public class DeadLineAction extends Action {

    private AppState applicationState;
    private String todo;
    private CustomDate date;
    private Priority priority;

    /**
     * Constructor for the {@code DeadLineAction class}
     *
     * @param applicationState Context of application from which to run the task from.
     * @param todo Description of the users todo.
     * @param date Deadline of the task.
     * @param priority The priority of the task, specified by the user (if any).
     */

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
        TaskList taskList = this.applicationState.getTaskList();
        TaskList newTaskList = taskList.addTask(new DeadLine(this.todo, false, this.date, this.priority));
        String response = String.format("Excellent! I've added this deadline:\n%s",
                taskList.showTask(taskList.size() - 1));
        return new AppState(newTaskList, response);
    }
}
