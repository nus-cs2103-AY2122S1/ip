package tiger.actions;

import tiger.app.AppState;

public class InvalidAction extends Action {
    AppState applicationState;

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
        return new AppState(false, this.applicationState.taskList, response);
    }
}
