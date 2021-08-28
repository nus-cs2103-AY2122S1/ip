package tiger.actions;

import tiger.app.AppState;

/**
 * Represents the action of the user inputting an invalid command.
 */

public class InvalidAction extends Action {

    private AppState applicationState;

    /**
     * Constructor for the {@code InvalidAction class}
     *
     * @param applicationState Context of application from which to run the task from.
     */

    public InvalidAction(AppState applicationState) {
        this.applicationState = applicationState;
    }

    /**
     * Runs the {@code Invalid} action. Used when the user enters an invalid input.
     *
     * @return an updated {@code AppState}.
     */


    @Override
    public AppState run() {
        String response = "Please enter in a valid command.";
        return new AppState(false, this.applicationState.getTaskList(), response);
    }
}
