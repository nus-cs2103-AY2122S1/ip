package jarvis.command;

import jarvis.exception.UnknownCommandException;
import jarvis.parser.Parser;

/**
 * Encapsulates all the possible and valid command types
 */
public enum CommandTypeEnum {
    DEADLINE("deadline"),
    DELETE("delete"),
    DONE("done"),
    EVENT("event"),
    EXIT("bye"),
    FIND("find"),
    LIST("list"),
    TODO("todo");

    private String commandTrigger;

    CommandTypeEnum(String commandTrigger) {
        this.commandTrigger = commandTrigger;
    }

    /**
     * Identifies the correct CommandTypeEnum depending on user input.
     *
     * @param userInput User input
     * @return The appropriate CommandTypeEnum
     * @throws UnknownCommandException If user input has no valid command type
     */
    public static CommandTypeEnum identifyCommandType(String userInput) throws UnknownCommandException {
        String commandTrigger = Parser.getCommandTriggerFromInput(userInput);

        for (CommandTypeEnum commandTypeEnum : CommandTypeEnum.values()) {
            if (commandTrigger.equals(commandTypeEnum.commandTrigger)) {
                return commandTypeEnum;
            }
        }

        throw new UnknownCommandException(commandTrigger);
    }

    /**
     * Gets the length of the command trigger string
     *
     * @return length of the command trigger string
     */
    public int getCommandTypeStringLength() {
        return commandTrigger.length();
    }
}
