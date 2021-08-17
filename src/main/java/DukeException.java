/**
 * Description:
 * DukeException class stores a variety of error messages that will be printed accordingly based on the
 * error encountered while Duke is running.
 *
 * @author Leong Hong Fai
 */

public class DukeException extends RuntimeException {
    private final String INVALIDTODO = "Enter a valid todo in this format 'todo <task here>'";
    private final String NOTASKSEXCEPTION = "There are no tasks in the list yet!";
    private final String INVALIDDEADLINE = "Enter a valid deadline in this format 'deadline <task> /by <date or day>'";
    private final String INVALIDEVENT = "Enter a valid event in this format 'event <task> /at <date or day>'";
    private final String INVALIDTASKNUMBER = "Current task number does not exist. Enter <list> to see all tasks";
    private final String INVALIDNUMBERFORMAT = "Enter a valid number in this format 'done <number>'";
    private final String INVALIDCOMMAND = "Enter a valid command!";
    private final String INVALIDNUMBERFORMATDELETE = "Enter a valid number in this format 'delete <number>'";
    private String type;
    private String errorMessage;


    public DukeException(String type) {
        this.type = type;
        switch (type) {
            case ("invalidToDo"):
                errorMessage = INVALIDTODO;
                break;
            case ("noTasksException"):
                errorMessage = NOTASKSEXCEPTION;
                break;
            case ("invalidDeadline"):
                errorMessage = INVALIDDEADLINE;
                break;
            case ("invalidEvent"):
                errorMessage = INVALIDEVENT;
                break;
            case ("invalidTaskNumber"):
                errorMessage = INVALIDTASKNUMBER;
                break;
            case ("invalidNumberFormat"):
                errorMessage = INVALIDNUMBERFORMAT;
                break;
            case ("invalidCommand"):
                errorMessage = INVALIDCOMMAND;
                break;
            case ("invalidNumberFormatDelete"):
                errorMessage = INVALIDNUMBERFORMATDELETE;
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
