package tiger.actions;

import tiger.app.AppState;

public class ListAction extends Action {
    AppState applicationState;

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
        String response = this.applicationState.taskList.toString();
        return new AppState(false, this.applicationState.taskList, response);
    }
}
