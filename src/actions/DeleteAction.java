package actions;

import components.TaskList;
import exceptions.DukeIndexOutOfBoundsException;

public class DeleteAction extends Action {
    AppState applicationState;
    int index;

    public DeleteAction(AppState applicationState, int index) {
        this.applicationState = applicationState;
        this.index = index;
    }

    public AppState run() {
        TaskList taskList = this.applicationState.taskList;
        TaskList newTaskList = taskList;
        try {
            System.out.println(String.format("Feeling lazy today? I've deleted:\n%s",
                    taskList.showTask(index)));
            newTaskList = taskList.deleteTask(index);
        } catch (DukeIndexOutOfBoundsException e) {
            System.out.println(e.toString());
        } finally {
            return new AppState(applicationState.userExit, newTaskList);
        }
    }
}
