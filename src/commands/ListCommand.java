package commands;

public class ListCommand extends Command {
    AppState applicationState;

    public ListCommand(AppState applicationState) {
        this.applicationState = applicationState;
    }

    @Override
    public AppState run() {
        System.out.println(applicationState.taskList.toString());
        return this.applicationState;
    }
}
