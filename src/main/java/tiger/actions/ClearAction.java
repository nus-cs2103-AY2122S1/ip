package tiger.actions;

import tiger.app.AppState;
import tiger.components.TaskList;

/**
 * Represents the action of clearing the task list.
 */

public class ClearAction extends Action {

    private AppState applicationState;

    /**
     * Constructor for the {@code ClearAction}
     *
     * @param applicationState Context of application from which to run the task from.
     */

    public ClearAction(AppState applicationState) {
        this.applicationState = applicationState;
    }

    /**
     * Runs the {@code List} action.
     *
     * @return an updated {@code AppState}.
     */

    @Override
    public AppState run() {
        String response = "I've cleared all your tasks!";
        return new AppState(false, new TaskList(), response);
    }
}
