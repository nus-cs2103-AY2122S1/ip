package tiger.actions;

public class ListAction extends Action {
    AppState applicationState;

    public ListAction(AppState applicationState) {
        this.applicationState = applicationState;
    }

    /**
     * Runs the {@code List} action.
     *
     * @return an updated {@code AppState}.
     */

    @Override
    public AppState run() {
        System.out.println(applicationState.taskList.toString());
        return this.applicationState;
    }
}
