package tiger.actions;

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
        System.out.println("Please enter in a valid command.");
        return this.applicationState;
    }
}
