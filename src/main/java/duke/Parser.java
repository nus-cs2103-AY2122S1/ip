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
     * @param scanner the scanner used to process user input
     */
    public static void evaluateUserInput(Scanner scanner) {
        String userInput = scanner.nextLine();

        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                TaskList.printList();
            } else if (userInput.startsWith("done") || userInput.startsWith("delete")) {
                try {
                    if (userInput.startsWith("done")) {
                        TaskList.markTaskDone(userInput);
                    } else {
                        TaskList.removeTask(userInput);
                    }
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Invalid input. Requires a number after done (e.g. done 1).");
                } catch (ArrayIndexOutOfBoundsException f) {
                    System.out.println("Invalid number - Number is larger than list count.");
                }
            } else if (userInput.startsWith("find")) {
                TaskList.findTask(userInput);
            } else {
                TaskList.addTask(userInput);
            }
            Storage.writeFile();
            userInput = scanner.nextLine();
        }

    }
}
