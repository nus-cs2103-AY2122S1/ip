package uhtredragnarson;

import java.time.format.DateTimeParseException;

/**
 * Parser parses the user input into a command and executes it.
 */
public class Parser {

    /**
     * This method parses the user input into a command and calls the appropriate method
     * in taskList to execute the command.
     *
     * @param userInput The input of the user.
     * @param taskList  The class that contains a list of tasks.
     * @param ui        Ui class to print messages.
     * @throws UhtredRagnarsonException Throws this exception if the user inputs an invalid command.
     */
    protected static String parse(String userInput, TaskList taskList, Ui ui) throws UhtredRagnarsonException {
        String result = "";
        String commandType = userInput.split(" ")[0];
        try {
            switch (commandType) {
            case "list":
                result = taskList.printTaskList();
                break;
            case "done":
                result = taskList.markTaskAsDone(userInput, ui);
                break;
            case "delete":
                result = taskList.deleteTask(userInput, ui);
                break;
            case "todo":
                result = taskList.addTodoTask(userInput, ui);
                break;
            case "deadline":
                result = taskList.addDeadlineTask(userInput, ui);
                break;
            case "event":
                result = taskList.addEventTask(userInput, ui);
                break;
            case "find":
                result = taskList.findTasks(userInput, ui);
                break;
            default:
                result = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
                throw new UhtredRagnarsonException(result);
            }
        } catch (DateTimeParseException e) {
            result = "☹ OOPS!!! You have to enter a valid date in the form "
                    + "yyyy-mm-dd";
            throw new UhtredRagnarsonException(result);
        } catch (UhtredRagnarsonException e) {
            result = e.getMessage();
            throw new UhtredRagnarsonException(result);
        }
        return result;
    }
}
