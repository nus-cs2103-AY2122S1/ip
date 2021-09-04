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
        if (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                return TaskList.printList();
            } else if (userInput.startsWith("done") || userInput.startsWith("delete")) {
                try {
                    if (userInput.startsWith("done")) {
                        return TaskList.markTaskDone(userInput);
                    } else {
                        return TaskList.removeTask(userInput);
                    }
                } catch (StringIndexOutOfBoundsException e) {
                    return "Invalid input. Requires a number after done (e.g. done 1).";
                } catch (ArrayIndexOutOfBoundsException f) {
                    return "Invalid number - Number is larger than list count.";
                }
            } else if (userInput.startsWith("find")) {
                return TaskList.findTask(userInput);
            } else {
                return TaskList.addTask(userInput);
            }
        }
        TaskList.markTasksSaved();
        return Ui.goodbyeMessage();
    }
}
