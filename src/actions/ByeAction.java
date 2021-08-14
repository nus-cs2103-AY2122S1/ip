package actions;


public class ByeAction extends Action {
    AppState applicationState;

    public ByeAction(AppState applicationState) {
        this.applicationState = applicationState;
    }

    @Override
    public AppState run() {
        return new AppState(true, this.applicationState.taskList);
    }
}
