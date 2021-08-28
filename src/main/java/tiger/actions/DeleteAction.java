package tiger.actions;

import tiger.app.AppState;
import tiger.components.TaskList;
import tiger.exceptions.actions.TigerIndexOutOfBoundsException;

/**
 * Represents the action of the user deleting a task.
 */

public class DeleteAction extends Action {

    private AppState applicationState;
    private int index;

    /**
     * Constructor for the {@code DeleteAction} class.
     *
     * @param applicationState Context of application from which to run the task from.
     * @param index Index of the task to delete.
     */

    public DeleteAction(AppState applicationState, int index) {
        this.applicationState = applicationState;
        this.index = index;
    }

    /**
     * Runs the {@code Delete} action.
     *
     * @return an updated {@code AppState}.
     */

    public AppState run() throws TigerIndexOutOfBoundsException {
        TaskList taskList = this.applicationState.getTaskList();
        String response = String.format("Feeling lazy today? I've deleted:\n%s",
                taskList.showTask(index));
        TaskList newTaskList = taskList.deleteTask(index);
        return new AppState(false, newTaskList, response);
    }
}
