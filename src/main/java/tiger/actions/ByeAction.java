package tiger.actions;


public class ByeAction extends Action {
    AppState applicationState;

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
        return new AppState(true, this.applicationState.taskList);
    }
}
