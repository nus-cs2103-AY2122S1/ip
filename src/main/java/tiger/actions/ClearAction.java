package tiger.actions;

import tiger.app.AppState;
import tiger.components.TaskList;

public class ClearAction extends Action {
    AppState applicationState;

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
