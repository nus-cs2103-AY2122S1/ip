/**
 *  A class that encapsulates the handling of the text input into Duke
 */
public class Parser {
    /**
     * Handles all the text input to call the correct corresponding method.
     *
     * @param input The text input from the user to Duke
     * @throws DukeException Exceptions specific to Duke's input
     * @return The corresponding String reply to the user's input
     */
    public String handleInput(TaskList list, String input) throws DukeException {
        String[] inputArray = input.split(" ",2);
        String reply = "";

        switch (inputArray[0]) {
        case "list":
            reply = list.displayTask();
            break;

        case "done":
            // Fallthrough
        case "delete":
            if (inputArray.length == 1) {
                throw new DukeException("The index is missing.");
            }
            reply = inputArray[0].equals("done") ? list.markTask(inputArray[1]) : list.deleteTask(inputArray[1]);
            break;

        case "todo":
            // Fallthrough
        case "deadline":
            // Fallthrough
        case "event":
            if (inputArray.length < 2 || inputArray[1].isBlank()) {
                throw new DukeException("The description of " + inputArray[0] + " cannot be empty.");
            }
            reply = list.addTask(Task.TaskName.getTaskType(inputArray[0]), inputArray[1]);
            break;

        default:
            throw new DukeException("I'm sorry, but I don't know what that means.");
        }
        return reply;
    }
}
