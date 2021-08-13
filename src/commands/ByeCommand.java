package commands;


public class ByeCommand extends Command {
    AppState applicationState;

    public ByeCommand(AppState applicationState) {
        this.applicationState = applicationState;
    }

    @Override
    public AppState run() {
        return new AppState(true, this.applicationState.taskList);
    }
}
