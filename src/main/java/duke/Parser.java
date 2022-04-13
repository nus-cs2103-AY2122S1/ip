package duke;

/**
 * This class deals with making sense of the user input/command
 * when using Duke.
 */
public class Parser {

    /**
     * Empty constructor for Parser.
     */
    public Parser() {
    }

    /**
     * Evaluates user input into understandable code for
     * Duke to process. Saves data whenever the TaskList changes.
     *
     * @param userInput what the user inputted into Duke.
     */
    public static String evaluateUserInput(String userInput) {
        boolean isQuitDuke = userInput.equals("bye") || userInput.equals("b");
        boolean isListRequest = userInput.equals("list") || userInput.equals("l");
        boolean isTaskCompleted = userInput.startsWith("done");
        boolean isRemoveTask = userInput.startsWith("delete") || userInput.startsWith("del ")
                || userInput.startsWith("rm ");
        boolean isFindTask = userInput.startsWith("find") || userInput.startsWith("f ");

        if (isQuitDuke) {
            TaskList.markTasksSaved();
            return Ui.goodbyeMessage();
        } else if (isListRequest) {
            return TaskList.printList();
        } else if (isTaskCompleted) {
            return TaskList.markTaskDone(userInput);
        } else if (isRemoveTask) {
            return TaskList.removeTask(userInput);
        } else if (isFindTask) {
            return TaskList.findTask(userInput);
        }

        return TaskList.addTask(userInput);
    }
}
