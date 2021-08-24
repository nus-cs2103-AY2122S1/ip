package jarvis.parser;

import jarvis.command.CommandTypeEnum;
import jarvis.exception.UnknownCommandException;

public class Parser {
    public static boolean isUserInputEmpty(String input) {
        return input.equals("");
    }

    public static boolean shouldExit(String input) throws UnknownCommandException {
        return CommandTypeEnum.identifyCommandType(input.trim()) == CommandTypeEnum.EXIT;
    }

    public static CommandTypeEnum getCommandTypeFromInput(String input) throws UnknownCommandException {
        return CommandTypeEnum.identifyCommandType(input.trim());
    }

    public static String getInputWithoutCommandType(String input, int commandTypeStringLength) {
        return input.trim().substring(commandTypeStringLength);
    }

    public static String getCommandTriggerFromInput(String input) {
        return input.split(" ", 2)[0];
    }

    public static String[] getDeadlineInfoArray(String userInputWithoutCommandTrigger) {
        return userInputWithoutCommandTrigger.split("/by", 2);
    }

    public static String[] getEventInfoArray(String userInputWithoutCommandTrigger) {
        return userInputWithoutCommandTrigger.split("/at", 2);
    }

    public static int getTaskIndex(String userInputWithoutCommandTrigger) {
        return Integer.parseInt(userInputWithoutCommandTrigger.trim()) - 1;
    }
}
