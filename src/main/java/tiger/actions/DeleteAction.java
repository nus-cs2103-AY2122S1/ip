package tiger.actions;

import tiger.components.TaskList;
import tiger.exceptions.inputs.TigerIndexOutOfBoundsException;

public class DeleteAction extends Action {
    AppState applicationState;
    int index;

    public DeleteAction(AppState applicationState, int index) {
        this.applicationState = applicationState;
        this.index = index;
    }

    public AppState run() throws TigerIndexOutOfBoundsException {
        TaskList taskList = this.applicationState.taskList;
        TaskList newTaskList = taskList;
        System.out.println(String.format("Feeling lazy today? I've deleted:\n%s",
                taskList.showTask(index)));
        newTaskList = taskList.deleteTask(index);
        return new AppState(applicationState.userExit, newTaskList);
    }
}
