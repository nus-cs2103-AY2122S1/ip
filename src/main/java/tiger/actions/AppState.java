package tiger.actions;

import tiger.components.TaskList;

/**
 * Represents the current state of the Application.
 */

public class AppState {
    /** Whether the user has exited the program*/
    protected boolean userExit;
    /** A {@code TaskList} to store user tasks */
    public TaskList taskList;

    // TODO: move AppState in their own class
    // TODO: have some object to get the response of the Ui, instead of doing System.out.println() at each component
    //  individually
    // TODO: set priority tasks

    /**
     * Public constructor for the {@code AppState} class.
     *
     * @param userExit whether the user has exited the program.
     * @param taskList a {@code TaskList} to store user tasks.
     */

    public AppState(boolean userExit, TaskList taskList) {
        this.userExit = userExit;
        this.taskList = taskList;
    }

    /**
     * Returns the number of tasks in the {@code TaskList}.
     *
     * @return the number of tasks in the {@code TaskList}.
     */

    public int numTasks() {
        return this.taskList.size();
    }

    /**
     * Checks if the user has exited the program.
     *
     * @return a boolean to indicate if the user has exited the program.
     */

    public boolean isExited() {
        return this.userExit;
    }
}
