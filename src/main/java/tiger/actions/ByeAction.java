package tiger.actions;


import tiger.app.AppState;

public class ByeAction extends Action {
    private AppState applicationState;

    public ByeAction(AppState applicationState) {
        this.applicationState = applicationState;
    }

    /**
     * Runs the {@code Bye} action.
     *
     * @return an updated {@code AppState}.
     */

    @Override
    public AppState run() {
        String response = "Bye. Hope to see you again soon!";
        return new AppState(true, this.applicationState.taskList, response);
    }
}
