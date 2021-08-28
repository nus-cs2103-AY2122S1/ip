package tiger.actions;

import tiger.app.AppState;
import tiger.components.TaskList;

/**
 * Represents the action of the user finding a task.
 */

public class FindAction extends Action {

    private AppState applicationState;
    private String findDescription;

    /**
     * Constructor for the {@code FindAction class}
     *
     * @param applicationState Context of application from which to run the task from.
     * @param findDescription Substring of the tasks the user wishes to find.
     */

    public FindAction(AppState applicationState, String findDescription) {
        this.applicationState = applicationState;
        this.findDescription = findDescription;
    }

    /**
     * Runs the {@code FindAction} action.
     *
     * @return an updated {@code AppState}.
     */

    public AppState run() {
        TaskList newTaskList = this.applicationState.getTaskList().findRelevantTasks(this.findDescription);
        String response = newTaskList.toString();
        return new AppState(this.applicationState.getTaskList(), response);
    }



}
