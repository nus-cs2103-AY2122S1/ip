package duke.tasks;

/**
 * Class to maintain the UI of the list of tasks
 */
public class TaskManagerUi {
    public TaskManager taskList;
    public String message;

    /**
     * Constructor for the TaskManagerUi class
     *
     * @param taskList - the list of tasks
     * @param message  - to display the relevant message
     */
    public TaskManagerUi(TaskManager taskList, String message) {
        this.taskList = taskList;
        this.message = message;
    }
}
