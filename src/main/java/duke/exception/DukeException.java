package duke.exception;

/**
 * Stores exceptions which generate a variety of error messages based on the error encountered during runtime.
 */
public class DukeException extends RuntimeException {
    private final String INVALID_TODO = "Enter a valid todo in this format 'todo <task here>'";
    private final String NO_TASKS_EXCEPTION = "There are no tasks in the list yet!";
    private final String INVALID_DEADLINE = "Enter a valid deadline in this format 'deadline <task> /by <date or day>'";
    private final String INVALID_EVENT = "Enter a valid event in this format 'event <task> /at <date or day>'";
    private final String INVALID_TASK_NUMBER = "Current task number does not exist. Enter <list> to see all tasks";
    private final String INVALID_NUMBER_FORMAT = "Enter a valid number in this format 'done <number>'";
    private final String INVALID_COMMAND = "Enter a valid command!";
    private final String INVALID_NUMBER_FORMAT_DELETE = "Enter a valid number in this format 'delete <number>'";
    private final String INVALID_FIND_TASK = "Enter a valid find task format 'find <task name>";
    private String type;
    private String errorMessage;


    /**
     * Creates DukeException object.
     *
     * @param type Type of error message to throw.
     */
    public DukeException(String type) {
        this.type = type;
        switch (type) {
        case ("invalidToDo"):
            errorMessage = INVALID_TODO;
            break;
        case ("invalidFindTask"):
            errorMessage = INVALID_FIND_TASK;
            break;
        case ("noTasksException"):
            errorMessage = NO_TASKS_EXCEPTION;
            break;
        case ("invalidDeadline"):
            errorMessage = INVALID_DEADLINE;
            break;
        case ("invalidEvent"):
            errorMessage = INVALID_EVENT;
            break;
        case ("invalidTaskNumber"):
            errorMessage = INVALID_TASK_NUMBER;
            break;
        case ("invalidNumberFormat"):
            errorMessage = INVALID_NUMBER_FORMAT;
            break;
        case ("invalidCommand"):
            errorMessage = INVALID_COMMAND;
            break;
        case ("invalidNumberFormatDelete"):
            errorMessage = INVALID_NUMBER_FORMAT_DELETE;
            break;
        default:
            errorMessage = "Unknown error has occurred.";
        }
    }

    /**
     * Simple string representation of DukeException.
     *
     * @return A string consisting of the relevant error message.
     */
    public String toString() {
        return errorMessage;
    }

}
