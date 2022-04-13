package tiger.actions;


import tiger.app.AppState;
import tiger.constants.Flag;
import tiger.constants.Messages;

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
        return new AppState(this.applicationState.getTaskList(), Messages.TIGER_BYE_MESSAGE.getMessage(),
                Flag.IS_EXITED);
    }
}
