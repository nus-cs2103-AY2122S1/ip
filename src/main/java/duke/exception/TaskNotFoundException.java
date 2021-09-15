package duke.exception;

public class TaskNotFoundException extends DukeException {

    private static final String message = "Task number %d not found! Try a positive number smaller or equal to %d";
    /**
     * Constructs a TaskNotFoundException instance that handles the situation where a task cannot be found.
     *
     * @param index The task index specified by the user.
     * @param totalTaskNum The total number of existing tasks.
     */
    public TaskNotFoundException(int index, int totalTaskNum) {
        super(String.format(message, index, totalTaskNum));
    }
}
