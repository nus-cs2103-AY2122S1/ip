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

    /**
     * Public constructor for the {@code AppState} class.
     *
     * @param taskList a {@code TaskList} to store user tasks.
     */

    public AppState(TaskList taskList) {
        this.taskList = taskList;
        this.response = "";
        this.flag = Flag.DEFAULT;
    }

    /**
     * Public constructor for the {@code AppState} class.
     *
     * @param taskList a {@code TaskList} to store user tasks.
     * @param response Response from the application, to be printed to the GUI.
     * @throws AssertionError if the response is empty.
     */

    public AppState(TaskList taskList, String response) throws AssertionError {
        this.taskList = taskList;
        assert (!response.equals(""));
        this.response = response;
        this.flag = Flag.DEFAULT;
    }

    /**
     * Public constructor for the {@code AppState} class.
     *
     * @param taskList a {@code TaskList} to store user tasks.
     * @param response Response from the application, to be printed to the GUI.
     * @param flag Flag to indicate other context information about the application.
     * @throws AssertionError if the response is empty.
     */

    public AppState(TaskList taskList, String response, Flag flag) throws AssertionError {
        this.taskList = taskList;
        assert (!response.equals(""));
        this.response = response;
        this.flag = flag;
    }

    /**
     * Public constructor for the {@code AppState} class.
     *
     * @param taskList a {@code TaskList} to store user tasks.
     * @param flag Flag to indicate other context information about the application.
     */

    public AppState(TaskList taskList, Flag flag) {
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
