package actions;

import components.TaskList;

public class AppState {
    protected boolean userExit;
    protected TaskList taskList;

    public AppState(boolean userExit, TaskList taskList) {
        this.userExit = userExit;
        this.taskList = taskList;
    }

    public boolean isExited() {
        return this.userExit;
    }
}
