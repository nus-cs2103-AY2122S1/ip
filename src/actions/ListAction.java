package actions;

public class ListAction extends Action {
    AppState applicationState;

    public ListAction(AppState applicationState) {
        this.applicationState = applicationState;
    }

    @Override
    public AppState run() {
        System.out.println(applicationState.taskList.toString());
        return this.applicationState;
    }
}
