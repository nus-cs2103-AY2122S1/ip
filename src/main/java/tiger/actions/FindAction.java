package tiger.actions;

import tiger.components.TaskList;

public class FindAction extends Action {

    AppState applicationState;
    String findDescription;

    public FindAction(AppState applicationState, String findDescription) {
        this.applicationState = applicationState;
        this.findDescription = findDescription;
    }

    /**
     * Runs the {@code FindAction} action.
     *
     * @return an updated {@code AppState}.
     */

    public AppState run() {
        TaskList newTaskList = this.applicationState.taskList.findRelevantTasks(this.findDescription);
        System.out.println(newTaskList);
        return this.applicationState;
    }



}
