package tiger.actions;

import tiger.app.AppState;
import tiger.components.TaskList;
import tiger.constants.Messages;

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
        return new AppState(new TaskList(), Messages.TIGER_CLEAR_MESSAGE.getMessage());
    }
}
