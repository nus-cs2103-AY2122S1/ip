package tiger.actions;

import tiger.app.AppState;

/**
 * Represents the action of the user listing all tasks.
 */

public class ListAction extends Action {

    private AppState applicationState;

    /**
     * Constructor for the {@code ListAction class}
     *
     * @param applicationState Context of application from which to run the task from.
     */

    public ListAction(AppState applicationState) {
        this.applicationState = applicationState;
    }

    /**
     * Runs the {@code List} action.
     *
     * @return an updated {@code AppState}.
     */

    @Override
    public AppState run() {
        String response = this.applicationState.getTaskList().toString();
        return new AppState(false, this.applicationState.getTaskList(), response);
    }
}
