import java.util.Arrays;
import java.util.Scanner;

/**
 * A class responsible for reading and evaluating user inputs.
 */
public class UserInputs {

    private TaskList taskList;

    public UserInputs() {
        taskList = new TaskList();
    }

    /**
     * Takes the user input to the chat bot.
     * @param scanner The scanner used to scan for user input.
     * @return The user input as a String.
     */
    public String getUserInput(Scanner scanner) {
        return scanner.nextLine();
    }

    /**
     * Evaluates a user's input to the chat bot and carries out the appropriate functions.
     * @param input The user's input.
     * @return False if the user's input is supposed to close the chat bot. Else, the chat bot continues
     * and wait for the user to input another word.
     */
    public boolean evaluateUserInput(String input) {
        if (input.equalsIgnoreCase("bye")) {
            // Ends the chat
            return false;
        } else if (input.equalsIgnoreCase("list")) {
            // Shows the task history
            this.taskList.listHistory();
            return true;
        } else if (input.toLowerCase().startsWith("done")) {
            // Sets a task as done
            int index = Integer.parseInt(input.split(" ")[1]);
            this.taskList.markTaskAsCompleted(index);
            return true;
        } else if (input.toLowerCase().startsWith("todo")) {
            // Creates a todo task
            Task task = Todo.newTodoTask(UserInputs.removeFirstWordFromString(input));
            this.taskList.addTask(task);
            return true;
        } else if (input.toLowerCase().startsWith("deadline")) {
            //Creates a deadline task
            Task task = Deadline.newDeadlineTask(UserInputs.removeFirstWordFromString(input));
            this.taskList.addTask(task);
            return true;
        } else if (input.toLowerCase().startsWith("event")) {
            // Creates an event task
            Task task = Event.newEventTask(UserInputs.removeFirstWordFromString(input));
            this.taskList.addTask(task);
            return true;
        }
        // Unrecognised input
        System.out.println("Invalid input. Please try again.\n");
        return true;
    }

    private static String removeFirstWordFromString(String string) {
        return string.split(" ", 2)[1];
    }
}
