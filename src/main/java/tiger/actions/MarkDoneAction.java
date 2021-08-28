package tiger.actions;

import tiger.app.AppState;
import tiger.components.TaskList;
import tiger.exceptions.actions.TigerIndexOutOfBoundsException;

/**
 * Represents the action of the user marking a task as done.
 */


public class MarkDoneAction extends Action {

    private AppState applicationState;
    private int index;

    /**
     * Constructor for the {@code MarkDoneAction} class.
     *
     * @param applicationState Context of application from which to run the task from.
     * @param index Index of the task to mark done..
     */

    public MarkDoneAction(AppState applicationState, int index) {
        this.applicationState = applicationState;
        this.index = index;
    }

    /**
     * Runs the {@code MarkDoneAction} action.
     *
     * @return an updated {@code AppState}.
     */

    public AppState run() throws TigerIndexOutOfBoundsException {
        TaskList taskList = this.applicationState.getTaskList();
        TaskList newTaskList = taskList.markTaskDone(index);
        String response = String.format("Nice! I've marked this task as done:\n%s",
                newTaskList.showTask(index));
        return new AppState(false, newTaskList, response);
    }
}
