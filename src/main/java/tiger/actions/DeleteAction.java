package tiger.actions;

import tiger.app.AppState;
import tiger.components.TaskList;
import tiger.exceptions.actions.TigerIndexOutOfBoundsException;

public class DeleteAction extends Action {
    private AppState applicationState;
    private int index;

    public DeleteAction(AppState applicationState, int index) {
        this.applicationState = applicationState;
        this.index = index;
    }

    public AppState run() throws TigerIndexOutOfBoundsException {
        TaskList taskList = this.applicationState.taskList;
        String response = String.format("Feeling lazy today? I've deleted:\n%s",
                taskList.showTask(index));
        TaskList newTaskList = taskList.deleteTask(index);
        return new AppState(false, newTaskList, response);
    }
}
