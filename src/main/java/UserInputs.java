import java.util.Arrays;
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
     * Evaluates a user's input to the chat bot and carries out the appropriate functions.
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
        } else if (input.toLowerCase().startsWith("done")) {
            // Sets a task as done
            int index = Integer.parseInt(input.split(" ")[1]);
            TaskList.markTaskAsCompleted(index);
            return true;
        } else if (input.toLowerCase().startsWith("todo")) {
            // Creates a todo task
            System.out.println("todo");
            Task task = Todo.newTodoTask(UserInputs.removeFirstWordFromString(input));
            TaskList.addTask(task);
            return true;
        } else if (input.toLowerCase().startsWith("deadline")) {
            //Creates a deadline task
            Task task = Deadline.newDeadlineTask(UserInputs.removeFirstWordFromString(input));
            TaskList.addTask(task);
            return true;
        } else if (input.toLowerCase().startsWith("event")) {
            // Creates an event task
            Task task = Event.newEventTask(UserInputs.removeFirstWordFromString(input));
            TaskList.addTask(task);
            return true;
        }
        // Unrecognised input
        System.out.println("Invalid input. Please try again.");
        return true;
    }

    private static String removeFirstWordFromString(String string) {
        return string.split(" ", 2)[1];
    }
}
