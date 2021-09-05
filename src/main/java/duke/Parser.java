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
        } else if (userInput.startsWith("done ")) {
            return Command.DONE;
        } else if (userInput.startsWith("delete ")) {
            return Command.DELETE;
        } else if (userInput.startsWith("todo ")) {
            return Command.TODO;
        } else if (userInput.startsWith("deadline ")) {
            return Command.DEADLINE;
        } else if (userInput.startsWith("event ")) {
            return Command.EVENT;
        } else if (userInput.startsWith("find ")) {
            return Command.FIND;
        } else if (userInput.equals("clear")) {
            return Command.CLEAR;
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
    public static Task getTask(Command taskType, String userInput) throws IllegalArgumentException {
        int indexOfFirstSpace = userInput.indexOf(' ');
        userInput = userInput.substring(indexOfFirstSpace + 1);
        if (taskType == Command.TODO) {
            if (indexOfFirstSpace == -1) {
                throw new IllegalArgumentException("Description of a todo cannot be empty (e.g. todo <description>)");
            }
            return new Todo(userInput);
        } else if (taskType == Command.DEADLINE) {
            String[] args = userInput.split(" /by ");
            if (args.length != 2) {
                throw new IllegalArgumentException("Invalid deadline format (e.g. deadline <description> /by <date>)");
            }
            return new Deadline(args[0], args[1]);
        } else if (taskType == Command.EVENT) {
            String[] args = userInput.split(" /at ");
            if (args.length != 2) {
                throw new IllegalArgumentException("Invalid event format (e.g. event <description> /at <date>)\")");
            }
            return new Event(args[0], args[1]);
        } else {
            throw new IllegalArgumentException("Unable to interpret task type");
        }
    }

}
