package virushade;

import virushade.tasks.TaskList;

/**
 * A utility class that deals with interactions with the user.
 */
public class ResponseProcessor {

    /**
     * Prints the greeting.
     */
    public static String greet() {
        return "Hello from\n"
                + "__      __ ( )                ____\n"
                + "\\ \\    / /  _   ____  _   _  / __/\n"
                + " \\ \\  / /  | | | ,__|| | | | \\ \\\n"
                + "  \\ \\/ /   | | | |   | |_| | _) \\\n"
                + "   \\__/    |_| |_|    \\__,_||___/\n"
                + "What can <<Virushade>> do for you?\n";
    }

    /**
     * Virushade first says goodbye before exiting.
     */
    private static String exitMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Handles unexpected inputs to Virushade
     * @param str The input.
     * @throws VirushadeException The response error message for the user.
     */
    private static String handleSingleWordInput(String str) throws VirushadeException {
        switch(str) {
        case "bye":
            return exitMessage();

        case "list":
            return TaskList.generateList();

        case "todo":
            throw new VirushadeException("OOPS!!! The description of a todo task cannot be empty.");

        case "deadline":
            throw new VirushadeException("OOPS!!! The description of a deadline task cannot be empty.");

        case "event":
            throw new VirushadeException("OOPS!!! The description of an event task cannot be empty.");

        case "done":
            throw new VirushadeException("OOPS!!! Please enter an integer after 'done'.");

        case "find":
            throw new VirushadeException("OOPS!!! Please tell me what to find.");

        default:
            throw new VirushadeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Determines what Virushade should do with given string str.
     * Note: str is assumed to not be "bye".
     *
     * @param str The input instructional string.
     */
    private static String assignTask(String str) throws VirushadeException {
        String keyword = StringManipulator.wordSeparator(str)[0];
        String instruction = StringManipulator.wordSeparator(str)[1];

        if (!instruction.equals("")) {
            switch (keyword) {
            case "find":
                return TaskList.findFromList(instruction);

            case "done":
                return TaskList.completeTask(instruction);

            case "todo":
                return TaskList.add(instruction, "TODO");

            case "deadline":
                return TaskList.add(instruction, "DEADLINE");

            case "event":
                return TaskList.add(instruction, "EVENT");

            case "delete":
                return TaskList.delete(instruction);
            }
        }
        return handleSingleWordInput(keyword);
    }

    /**
     * Returns a string according to the input string str.
     *
     * @param str The input string.
     * @return The resultant string message.
     */
    public static String handleMessage(String str) {
        String resultantString;

        try {
            resultantString = assignTask(str);
        } catch (VirushadeException e) {
            resultantString = handleVirushadeException(e);
        }

        return resultantString;
    }

    /**
     * Notifies the user that Virushade is creating files.
     * @return A string message telling the user that the file is under creation.
     */
    public static String showCreatingFiles() {
        return "Creating data files...";
    }

    /**
     * Notifies the user that Virushade is creating directories.
     * @return A string message telling the user that the file is under creation.
     */
    public static String showCreatingDirectory() {
        return "Creating data directory...";
    }

    /**
     * Handles mostly incorrect inputs by the user.
     * @param e The error message.
     * @return The error message in e.
     */
    public static String handleVirushadeException(VirushadeException e) {
        return e.getMessage();
    }
}
