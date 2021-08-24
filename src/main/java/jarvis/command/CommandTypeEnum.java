package jarvis.command;

import jarvis.exception.UnknownCommandException;
import jarvis.parser.Parser;

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

    public int getCommandTypeStringLength() {
        return commandTrigger.length();
    }

    public static CommandTypeEnum identifyCommandType(String userInput) throws UnknownCommandException {
        String commandTrigger = Parser.getCommandTriggerFromInput(userInput);

        for (CommandTypeEnum commandTypeEnum : CommandTypeEnum.values()) {
            if (commandTrigger.equals(commandTypeEnum.commandTrigger)) {
                return commandTypeEnum;
            }
        }

        throw new UnknownCommandException(commandTrigger);
    }
}
