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
                + "\\ \\    / /  _   _____  _  / __/\n"
                + " \\ \\  / /  | | | ,__|| | | | \\ \\\n"
                + "  \\ \\/ /   | | | |   | |_| | _) \\\n"
                + "   \\__/    |_| |_|   \\__,_||___/\n"
                + "Who do you want <<Virushade>> to infect today?\n";
    }

    /**
     * Virushade first says goodbye before exiting.
     * @return The exit message.
     */
    private static String exitMessage() {
        return "You may run, you may hide, but you will never escape my infections!";
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
            throw new VirushadeException("The description of a todo task cannot be empty.");

        case "deadline":
            throw new VirushadeException("The description of a deadline task cannot be empty.");

        case "event":
            throw new VirushadeException("The description of an event task cannot be empty.");

        case "done":
            throw new VirushadeException("Let me know what task needs to be marked done.");

        case "find":
            throw new VirushadeException("Give me something to find, human.");

        case "sort":
            throw new VirushadeException("If you want me to do work, give me the right parameters first!\n"
                    + "Let me know which sorting method you want to use, human.");

        default:
            throw new VirushadeException("I have yet to evolve to a level where I can understand this.");
        }
    }

    /**
     * Determines what Virushade should do with given string str.
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

            case "sort":
                return TaskList.sort(instruction);

            default:
                throw new VirushadeException("I have yet to evolve to a level where I can understand this.");
            }
        }
        return handleSingleWordInput(keyword);
    }

    /**
     * Returns a string according to the input string str.
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
     * Handles mostly incorrect inputs by the user.
     * @param e The error message.
     * @return The error message in e.
     */
    public static String handleVirushadeException(VirushadeException e) {
        return e.getMessage();
    }
}
