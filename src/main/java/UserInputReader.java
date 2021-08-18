import java.util.Scanner;

/**
 * A class responsible for reading and evaluating user inputs.
 */
public class UserInputReader {

    private final static String KEYWORD_DONE = "done";
    private final static String KEYWORD_LIST = "list";
    private final static String KEYWORD_BYE = "bye";
    private final static String KEYWORD_TODO = "todo";
    private final static String KEYWORD_DEADLINE = "deadline";
    private final static String KEYWORD_EVENT = "event";
    private final static String KEYWORD_DELETE = "delete";

    private final TaskList taskList;
    private final Scanner scanner;

    public UserInputReader(Scanner scanner) {
        taskList = new TaskList();
        this.scanner = scanner;
    }

    /**
     * Takes the user input to the chat bot and evaluates it.
     * @return The user input as a String.
     */
    public String getUserInput() {
        return this.scanner.nextLine();
    }

    /**
     * Evaluates a user's input to the chat bot and carries out the appropriate functions.
     * @param input The user's input.
     * @return False if the user's input is supposed to close the chat bot. Else, the chat bot continues
     * and wait for the user to input another word.
     */
    public boolean evaluateUserInput(String input) {
        if (input.equalsIgnoreCase(KEYWORD_BYE)) {
            // Ends the chat
            return false;
        } else if (input.equalsIgnoreCase(KEYWORD_LIST)) {
            // Shows the task history
            this.taskList.listHistory();
            return true;
        } else if (input.toLowerCase().startsWith(KEYWORD_DONE)) {
            // Sets a task as done
            this.markTaskAsDone(input);
            return true;
        } else if (input.toLowerCase().startsWith(KEYWORD_TODO)) {
            // Creates a todo task
            String details = UserInputReader.removeFirstWordFromString(input, Task.Type.TODO);
            if (details != null && details.trim().length() > 0) {
                Task task = Todo.newTodoTask(details);
                this.taskList.addTask(task);
            }
            return true;
        } else if (input.toLowerCase().startsWith(KEYWORD_DEADLINE)) {
            //Creates a deadline task
            String details = UserInputReader.removeFirstWordFromString(input, Task.Type.DEADLINE);
            if (details != null && this.verifyDeadlineInput(details.trim())) {
                Task task = Deadline.newDeadlineTask(details);
                this.taskList.addTask(task);
            }
            return true;
        } else if (input.toLowerCase().startsWith(KEYWORD_EVENT)) {
            // Creates an event task
            String details = UserInputReader.removeFirstWordFromString(input, Task.Type.EVENT);
            if (details != null && this.verifyEventInput(details.trim())) {
                Task task = Event.newEventTask(details);
                this.taskList.addTask(task);
            }
            return true;
        } else if (input.toLowerCase().startsWith(KEYWORD_DELETE)) {
            // Sets a task as done
            this.deleteATask(input);
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
            UserInputReader.printErrorMessage(Task.Type.DEADLINE);
            return false;
        }
        String[] inputParts = input.split(" -by ");
        if (inputParts.length != 2) {
            UserInputReader.printErrorMessage(Task.Type.DEADLINE);
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
            UserInputReader.printErrorMessage(Task.Type.EVENT);
            return false;
        }
        String[] inputParts = input.split(" -at ");
        if (inputParts.length != 2 || inputParts[0].trim().length() == 0 || inputParts[1].trim().length() == 0) {
            UserInputReader.printErrorMessage(Task.Type.EVENT);
            return false;
        }
        return true;
    }

    /**
     * Marks a task as done based on the user's input after verifying that the user input is valid.
     * @param input The user's input.
     */
    public void markTaskAsDone(String input) {
        try {
            int index = Integer.parseInt(input.split(" ", 2)[1].trim());
            this.taskList.markTaskAsCompleted(index);
        } catch (NumberFormatException e) {
            System.out.println("Invalid argument to the \"done\" function.\n");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("No argument supplied to the \"done\" function.\n");
        }
    }

    /**
     * Removes a task based on the user's input after verifying that the user input is valid..
     * @param input The user's input.
     */
    public void deleteATask(String input) {
        try {
            int index = Integer.parseInt(input.split(" ", 2)[1].trim());
            this.taskList.removeTask(index);
        } catch (NumberFormatException e) {
            System.out.println("Invalid argument to the \"delete\" function.\n");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("No argument supplied to the \"delete\" function.\n");
        }
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
            UserInputReader.printErrorMessage(type);
            return null;
        }
    }
}
