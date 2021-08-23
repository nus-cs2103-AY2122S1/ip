package tiger.actions;

import tiger.app.AppState;
import tiger.components.TaskList;
import tiger.exceptions.actions.TigerIndexOutOfBoundsException;

public class MarkDoneAction extends Action {
    private AppState applicationState;
    private int index;

    public MarkDoneAction(AppState applicationState, int index) {
        this.applicationState = applicationState;
        this.index = index;
    }

    public AppState run() throws TigerIndexOutOfBoundsException {
        TaskList taskList = this.applicationState.taskList;
        TaskList newTaskList = taskList.markTaskDone(index);
        String response = String.format("Nice! I've marked this task as done:\n%s",
                newTaskList.showTask(index));
        return new AppState(false, newTaskList, response);
    }
}
