package actions;

import components.TaskList;
import exceptions.DukeIndexOutOfBoundsException;

public class MarkDoneAction extends Action {
    AppState applicationState;
    int index;

    public MarkDoneAction(AppState applicationState, int index) {
        this.applicationState = applicationState;
        this.index = index;
        TaskList taskList = this.applicationState.taskList;
    }

    public AppState run() {
        TaskList taskList = this.applicationState.taskList;
        TaskList newTaskList = taskList;
        try {
            newTaskList = taskList.markTaskDone(index);
            System.out.println(String.format("Nice! I've marked this task as done:\n%s",
                    newTaskList.showTask(index)));
        } catch (DukeIndexOutOfBoundsException e) {
            System.out.println(e.toString());
        } finally {
            return new AppState(applicationState.userExit, newTaskList);
        }

    }
}
