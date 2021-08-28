package tiger.actions;


import tiger.app.AppState;

/**
 * Represents the action of the user leaving the app.
 */

public class ByeAction extends Action {

    private AppState applicationState;

    /**
     * Constructor for {@code ByeAction}.
     *
     * @param applicationState Context of application from which to run the task from.
     */

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
        return new AppState(true, this.applicationState.getTaskList(), response);
    }
}
