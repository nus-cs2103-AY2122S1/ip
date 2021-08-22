package tiger.actions;

import tiger.components.TaskList;
import tiger.exceptions.inputs.TigerIndexOutOfBoundsException;

public class MarkDoneAction extends Action {
    AppState applicationState;
    int index;

    public MarkDoneAction(AppState applicationState, int index) {
        this.applicationState = applicationState;
        this.index = index;
    }

    public AppState run() throws TigerIndexOutOfBoundsException {
        TaskList taskList = this.applicationState.taskList;
        TaskList newTaskList = taskList;
        newTaskList = taskList.markTaskDone(index);
        System.out.println(String.format("Nice! I've marked this task as done:\n%s",
                newTaskList.showTask(index)));
        return new AppState(applicationState.userExit, newTaskList);
    }
}
