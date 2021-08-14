package actions;

public class InvalidAction extends Action {
    AppState applicationState;

    public InvalidAction(AppState applicationState) {
        this.applicationState = applicationState;
    }

    @Override
    public AppState run() {
        System.out.println("Please enter in a valid command.");
        return this.applicationState;
    }
}
