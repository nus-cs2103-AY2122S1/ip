import java.util.Scanner;

/**
 * A class responsible for reading and evaluating user inputs.
 */
public class UserInputs {

    private final TaskList taskList;

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
            try {
                int index = Integer.parseInt(input.split(" ")[1]);
                this.taskList.markTaskAsCompleted(index);
            } catch (NumberFormatException e) {
                System.out.println("Invalid argument to the \"done\" function.\n");
            }
            return true;
        } else if (input.toLowerCase().startsWith("todo")) {
            // Creates a todo task
            String details = UserInputs.removeFirstWordFromString(input, Task.Type.TODO);
            if (details != null && details.trim().length() > 0) {
                Task task = Todo.newTodoTask(details);
                this.taskList.addTask(task);
            }
            return true;
        } else if (input.toLowerCase().startsWith("deadline")) {
            //Creates a deadline task
            String details = UserInputs.removeFirstWordFromString(input, Task.Type.DEADLINE);
            if (details != null && this.verifyDeadlineInput(details.trim())) {
                Task task = Deadline.newDeadlineTask(details);
                this.taskList.addTask(task);
            }
            return true;
        } else if (input.toLowerCase().startsWith("event")) {
            // Creates an event task
            String details = UserInputs.removeFirstWordFromString(input, Task.Type.EVENT);
            if (details != null && this.verifyEventInput(details.trim())) {
                Task task = Event.newEventTask(details);
                this.taskList.addTask(task);
            }
            return true;
        }
        // Unrecognised input
        System.out.println("Invalid input. Please try again.\n");
        return true;
    }

    /**
     * Verifies that the deadline task details are correct. It checks that the user has used the
     * command "-by" and that a non-empty date and time is specified. If it is not correct, it
     * prints an error message.
     * @param input The deadline details.
     * @return True if the deadline details inputted by the user is correct. Otherwise, false.
     */
    public boolean verifyDeadlineInput(String input) {
        if (!input.contains("-by")) {
            UserInputs.printErrorMessage(Task.Type.DEADLINE);
            return false;
        }
        String[] inputParts = input.split(" -by ");
        if (inputParts.length != 2) {
            UserInputs.printErrorMessage(Task.Type.DEADLINE);
            return false;
        }
        return true;
    }

    /**
     * Verifies the event task details is correct. It checks that the user has used the command
     * "-at" and that a non-empty date and time is specified. If is not correct, it prints
     * an error message.
     * @param input The event details.
     * @return True if the event details inputted by the user is correct. Otherwise, false.
     */
    public boolean verifyEventInput(String input) {
        if (!input.contains("-at")) {
            UserInputs.printErrorMessage(Task.Type.EVENT);
            return false;
        }
        String[] inputParts = input.split(" -at ");
        if (inputParts.length != 2 || inputParts[0].trim().length() == 0 || inputParts[1].trim().length() == 0) {
            UserInputs.printErrorMessage(Task.Type.EVENT);
            return false;
        }
        return true;
    }

    /**
     * Prints an error message when the user inputs the task but in the wrong message. The message
     * tells the user that an error has occurred and how to correctly the input the respective task.
     * @param type The type of task inputted by the user.
     */
    public static void printErrorMessage(Task.Type type) {
        if (type == Task.Type.TODO) {
            System.out.println("Invalid format. Please enter the todo format as below:");
            System.out.println(Duke.TODO_FORMAT + "\n");
        } else if (type == Task.Type.DEADLINE) {
            System.out.println("Invalid format. Please enter the deadline format as below:");
            System.out.println(Duke.DEADLINE_FORMAT + "\n");
        } else {
            System.out.println("Invalid format. Please enter the event format as below:");
            System.out.println(Duke.EVENT_FORMAT + "\n");
        }
    }

    private static String removeFirstWordFromString(String string, Task.Type type) {
        try {
            return string.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            UserInputs.printErrorMessage(type);
            return null;
        }
    }
}
