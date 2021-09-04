package tiger.actions;

import tiger.app.AppState;
import tiger.components.TaskList;
import tiger.constants.Messages;
import tiger.constants.Priority;

/**
 * Represents the action of the user finding a task.
 */

public class PriorityAction extends Action {

    private AppState applicationState;
    private Priority findPriority;

    /**
     * Constructor for the {@code FindAction class}
     *
     * @param applicationState Context of application from which to run the task from.
     * @param findPriority Substring of the tasks the user wishes to find.
     */

    public PriorityAction(AppState applicationState, Priority findPriority) {
        this.applicationState = applicationState;
        this.findPriority = findPriority;
    }

    /**
     * Runs the {@code FindAction} action.
     *
     * @return an updated {@code AppState}.
     */

    public AppState run() {
        TaskList newTaskList = this.applicationState.getTaskList().findTasksByPriority(this.findPriority);
        String response;
        if (newTaskList.size() == 0) {
            response = Messages.TIGER_CANNOT_FIND_TASKS_MESSAGE.getMessage();
        } else {
            response = newTaskList.toString();
        }
        return new AppState(this.applicationState.getTaskList(), response);
    }
}
