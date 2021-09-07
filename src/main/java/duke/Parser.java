package duke;

import java.util.Scanner;

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
        boolean isQuitDuke = userInput.equals("bye");
        boolean isListRequest = userInput.equals("list");
        boolean isTaskCompleted = userInput.startsWith("done");
        boolean isRemoveTask = userInput.startsWith("delete");
        boolean isFindTask = userInput.startsWith("find");

        if (isQuitDuke) {
            TaskList.markTasksSaved();
            return Ui.goodbyeMessage();
        }

        if (isListRequest) {
            return TaskList.printList();
        }

        if (isTaskCompleted) {
            return TaskList.markTaskDone(userInput);
        }

        if (isRemoveTask) {
            return TaskList.removeTask(userInput);
        }

        if (isFindTask) {
            return TaskList.findTask(userInput);
        }

        return TaskList.addTask(userInput);
    }
}
