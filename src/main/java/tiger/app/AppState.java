package tiger.app;

import tiger.components.TaskList;
import tiger.constants.Flag;

/**
 * Represents the current state of the Application.
 */

public class AppState {
    /** A {@code TaskList} to store user tasks */
    private TaskList taskList;
    /** String to store the response in */
    private String response;
    /** Flag in case some tasks failed to execute properly, or require subsequent follow-ups. Used to indicate other
     * context info about the app. */
    private Flag flag;
    /** Whether the user has exited the program*/
    private boolean userExit;

    /**
     * Public constructor for the {@code AppState} class.
     *
     * @param userExit whether the user has exited the program.
     * @param taskList a {@code TaskList} to store user tasks.
     */

    public AppState(boolean userExit, TaskList taskList) {
        this.userExit = userExit;
        this.taskList = taskList;
        this.response = "";
        this.flag = Flag.DEFAULT;
    }

    /**
     * Public constructor for the {@code AppState} class.
     *
     * @param userExit whether the user has exited the program.
     * @param taskList a {@code TaskList} to store user tasks.
     * @param response Response from the application, to be printed to the GUI.
     * @throws AssertionError if the response is empty.
     */

    public AppState(boolean userExit, TaskList taskList, String response) throws AssertionError {
        this.userExit = userExit;
        this.taskList = taskList;
        assert (!response.equals(""));
        this.response = response;
        this.flag = Flag.DEFAULT;
    }

    /**
     * Public constructor for the {@code AppState} class.
     *
     * @param userExit whether the user has exited the program.
     * @param taskList a {@code TaskList} to store user tasks.
     * @param response Response from the application, to be printed to the GUI.
     * @param flag Flag to indicate other context information about the application.
     * @throws AssertionError if the response is empty.
     */

    public AppState(boolean userExit, TaskList taskList, String response, Flag flag) throws AssertionError {
        this.userExit = userExit;
        this.taskList = taskList;
        assert (!response.equals(""));
        this.response = response;
        this.flag = flag;
    }

    /**
     * Public constructor for the {@code AppState} class.
     *
     * @param userExit whether the user has exited the program.
     * @param taskList a {@code TaskList} to store user tasks.
     * @param flag Flag to indicate other context information about the application.
     */

    public AppState(boolean userExit, TaskList taskList, Flag flag) {
        this.userExit = userExit;
        this.taskList = taskList;
        this.response = "";
        this.flag = flag;
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

    /**
     * Gets the response of the {@code AppState}
     *
     * @return the response of the {@code AppState}
     */

    public String getResponse() {
        return this.response;
    }

    /**
     * Gets the flag of the {@code AppState}.
     *
     * @return the flag of the {@code AppState}.
     */

    public Flag checkFlag() {
        return this.flag;
    }

    /**
     * Gets the {@code TaskList} object.
     *
     * @return the {@code TaskList} object.
     */

    public TaskList getTaskList() {
        return this.taskList;
    }

}
