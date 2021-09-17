package duke;

/**
 * Helps to parse user input into program understandable structure
 */
public class Parser {

    /**
     * Returns the command given by user in a Command enum format.
     *
     * @param userInput string input from user
     * @return a Command enum
     */
    public static Command getCommand(String userInput) {
        if (userInput.equals("bye")) {
            return Command.BYE;
        } else if (userInput.equals("list")) {
            return Command.LIST;
        } else if (userInput.equals("done") || userInput.startsWith("done ")) {
            return Command.DONE;
        } else if (userInput.equals("delete") || userInput.startsWith("delete ")) {
            return Command.DELETE;
        } else if (userInput.equals("todo") || userInput.startsWith("todo ")) {
            return Command.TODO;
        } else if (userInput.equals("deadline") || userInput.startsWith("deadline ")) {
            return Command.DEADLINE;
        } else if (userInput.equals("event") || userInput.startsWith("event ")) {
            return Command.EVENT;
        } else if (userInput.equals("find") || userInput.startsWith("find ")) {
            return Command.FIND;
        } else if (userInput.equals("clear")) {
            return Command.CLEAR;
        } else if (userInput.equals("undo")) {
            return Command.UNDO;
        } else if (userInput.equals("hello") || userInput.equals("hi")) {
            return Command.HELLO;
        } else if (userInput.equals("help")) {
            return Command.HELP;
        } else { // Invalid input
            return Command.INVALID;
        }
    }

    /**
     * Parses the user input of a task command and returns its Task object
     *
     * @param taskType the task type of enum Command (e.g. Command.EVENT)
     * @param userInput string input from user
     * @return a Task object
     */
    protected static Task getTask(Command taskType, String userInput) throws IllegalArgumentException {
        int indexOfFirstSpace = userInput.indexOf(' ');
        userInput = userInput.substring(indexOfFirstSpace + 1);
        if (taskType == Command.TODO) {
            if (indexOfFirstSpace == -1) {
                throw new IllegalArgumentException(
                        "Description of a todo cannot be empty.\n" +
                        "Please enter as: todo <description>\n" +
                        "e.g. todo read book");
            }
            return new Todo(userInput);
        } else if (taskType == Command.DEADLINE) {
            String[] args = userInput.split(" /by ");
            if (args.length != 2) {
                throw new IllegalArgumentException(
                        "The deadline format is not valid.\n" +
                        "Please enter as:\n" +
                        "deadline <description> /by <d/m/y hhmm>\n" +
                        "e.g. deadline return book /by 17/9/2021 2100");
            }
            return new Deadline(args[0], args[1]);
        } else if (taskType == Command.EVENT) {
            String[] args = userInput.split(" /at ");
            if (args.length != 2) {
                throw new IllegalArgumentException(
                        "The event format is not valid.\n" +
                        "Please enter as:\n" +
                        "event <description> /at <time range>\n" +
                        "e.g. event book discussion /at Mon 2-4pm");
            }
            return new Event(args[0], args[1]);
        } else {
            throw new IllegalArgumentException("Invalid task type");
        }
    }

}
