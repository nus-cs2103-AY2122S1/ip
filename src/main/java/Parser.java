import java.util.Scanner;

/**
 * A class responsible for reading and evaluating user inputs.
 */
public class Parser {

    /** User input keywords */
    private final static String KEYWORD_DONE = "done";
    private final static String KEYWORD_LIST = "list";
    private final static String KEYWORD_BYE = "bye";
    private final static String KEYWORD_TODO = "todo";
    private final static String KEYWORD_DEADLINE = "deadline";
    private final static String KEYWORD_EVENT = "event";
    private final static String KEYWORD_DELETE = "delete";

    /** A list of the tasks entered by the user */
    private final TaskList taskList;
    /** A scanner to scan for user inputs */
    private final Scanner scanner;

    public Parser(Scanner scanner) {
        taskList = new TaskList();
        this.scanner = scanner;
    }

    /**
     * Takes the user input to the chat bot and evaluates it.
     *
     * @return The user input as a String.
     */
    public String getUserInput() {
        return this.scanner.nextLine();
    }

    /**
     * Evaluates a user's input to the chat bot and carries out the appropriate functions. It also checks if the
     * user's input is a keyword to close the chat bot.
     *
     * @param input The user's input.
     * @return False if the user's input is supposed to close the chat bot. Else, the chat bot continues
     * and wait for the user to input another word.
     */
    public Command parseUserInput(String input) {
        if (input.equalsIgnoreCase(KEYWORD_BYE)) {
            // Ends the chat
            return new ExitCommand();
        } else if (input.equalsIgnoreCase(KEYWORD_LIST)) {
            // Shows the task history
            return new ListCommand(this.taskList);
        } else if (input.toLowerCase().startsWith(KEYWORD_DONE)) {
            // Sets a task as done
            return new DoneCommand(input, this.taskList);
        } else if (input.toLowerCase().startsWith(KEYWORD_TODO)) {
            // Creates a todo task
            return new AddTodoCommand(input, this.taskList);
        } else if (input.toLowerCase().startsWith(KEYWORD_DEADLINE)) {
            //Creates a deadline task
            return new AddDeadlineCommand(input, this.taskList);
        } else if (input.toLowerCase().startsWith(KEYWORD_EVENT)) {
            // Creates an event task
            return new AddEventCommand(input, this.taskList);
        } else if (input.toLowerCase().startsWith(KEYWORD_DELETE)) {
            // Sets a task as done
            return new DeleteCommand(input, this.taskList);
        }
        // Unrecognised input
        return new InvalidCommand();
    }


    public TaskList getTaskList() {
        return taskList;
    }
}
