import java.util.Scanner;

/**
 * A class containing static functions for reading and evaluating user inputs.
 */
public class UserInputs {

    /**
     * Takes the user input to the chat bot.
     * @param scanner The scanner used to scan for user input.
     * @return The user input as a String.
     */
    public static String getUserInput(Scanner scanner) {
        return scanner.nextLine();
    }

    /**
     * Evaluates a user's input to the chat bot.
     * @param input The user's input.
     * @return False if the user's input is supposed to close the chat bot. Else, the chat bot continues
     * and wait for the user to input another word.
     */
    public static boolean evaluateUserInput(String input) {
        if (input.equalsIgnoreCase("bye")) {
            // Ends the chat
            return false;
        } else if (input.equalsIgnoreCase("list")) {
            // Shows the task history
            TaskList.listHistory();
            return true;
        } else if (input.split(" ")[0].equalsIgnoreCase("done")) {
            int index = Integer.parseInt(input.split(" ")[1]);
            TaskList.markTaskAsCompleted(index);
            return true;
        }
        // Else add task to list
        Task newTask = new Task(input);
        TaskList.addTask(newTask);
        return true;
    }
}
