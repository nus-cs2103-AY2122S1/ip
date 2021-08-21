package tiger.actions;

import tiger.components.TaskList;

public class AppState {
    protected boolean userExit;
    public TaskList taskList;

    public AppState(boolean userExit, TaskList taskList) {
        this.userExit = userExit;
        this.taskList = taskList;
    }

    public int numTasks() {
        return this.taskList.size();
    }

    public boolean isExited() {
        return this.userExit;
    }
}
