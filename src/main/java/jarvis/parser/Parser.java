package jarvis.parser;

import jarvis.command.CommandTypeEnum;
import jarvis.exception.UnknownCommandException;

/**
 * Encapsulates a Parser that parses string inputs from the user.
 */
public class Parser {
    /**
     * Checks if user input is empty.
     *
     * @param input User input.
     * @return True if empty, false otherwise.
     */
    public static boolean isUserInputEmpty(String input) {
        return input.equals("");
    }

    /**
     * Checks if the exit command has been entered by user.
     *
     * @param input User input.
     * @return True if exit command entered, false otherwise.
     * @throws UnknownCommandException If the command is an unknown command.
     */
    public static boolean shouldExit(String input) throws UnknownCommandException {
        return CommandTypeEnum.identifyCommandType(input.trim()) == CommandTypeEnum.EXIT;
    }

    /**
     * Gets the command type from user input.
     *
     * @param input User input.
     * @return The appropriate CommandTypeEnum.
     * @throws UnknownCommandException If the command is an unknown command.
     */
    public static CommandTypeEnum getCommandTypeFromInput(String input) throws UnknownCommandException {
        return CommandTypeEnum.identifyCommandType(input.trim());
    }

    /**
     * Returns the user input without the command prefix.
     *
     * @param input User input.
     * @param commandTypeStringLength Length of the command type string.
     * @return A substring without the command prefix.
     */
    public static String getInputWithoutCommandType(String input, int commandTypeStringLength) {
        return input.trim().substring(commandTypeStringLength);
    }

    /**
     * Gets the command trigger from input.
     *
     * @param input User input.
     * @return Command trigger.
     */
    public static String getCommandTriggerFromInput(String input) {
        return input.split(" ", 2)[0];
    }

    /**
     * Returns all details needed by the deadline task.
     *
     * @param userInputWithoutCommandTrigger User input without command trigger.
     * @return An array of strings containing the details.
     */
    public static String[] getDeadlineInfoArray(String userInputWithoutCommandTrigger) {
        return userInputWithoutCommandTrigger.split("/by", 2);
    }

    /**
     * Returns all details needed by the event task.
     *
     * @param userInputWithoutCommandTrigger User input without command trigger.
     * @return An array of strings containing the details.
     */
    public static String[] getEventInfoArray(String userInputWithoutCommandTrigger) {
        return userInputWithoutCommandTrigger.split("/at", 2);
    }

    /**
     * Gets the task index number from user input.
     *
     * @param userInputWithoutCommandTrigger User input without command trigger.
     * @return The task index.
     */
    public static int getTaskIndex(String userInputWithoutCommandTrigger) {
        return Integer.parseInt(userInputWithoutCommandTrigger.trim()) - 1;
    }
}
