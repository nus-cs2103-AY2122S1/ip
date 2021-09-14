package jarvis.command;

import jarvis.exception.UnknownCommandException;
import jarvis.parser.Parser;
import jarvis.task.Deadline;
import jarvis.task.Event;

/**
 * Encapsulates all the possible and valid command types.
 */
public enum CommandTypeEnum {
    DEADLINE("deadline",
            "add a task with a specific deadline",
            String.format("deadline <description> <%s>", Deadline.INPUT_FORMAT)
    ),
    DELETE("delete",
            "delete a task using the task number shown in the tasks list",
            "delete <task number>"
    ),
    DONE("done",
            "mark a task as done using the task number shown in the tasks list",
            "done <task number>"
    ),
    EVENT("event",
            "add a event with a date, start time and end time",
            String.format(
                    "event <description> <%s %s %s>",
                    Event.INPUT_DATE_FORMAT,
                    Event.INPUT_TIME_FORMAT,
                    Event.INPUT_TIME_FORMAT
            )
    ),
    EXIT("bye",
    "quits the application",
            "bye"
    ),
    FIND("find",
            "finds all task that contains a keyword",
            "find <keyword>"
    ),
    HELP("help",
            "shows the help text for the application",
            "help"
    ),
    LIST("list",
            "shows the tasks list",
            "list"
    ),
    TODO("todo",
            "add a todo",
            "todo <description>"
    ),
    UNDO("undo",
            "undo the previous commands",
            "undo <undo amount>"
    );

    private String commandTrigger;
    private String usage;
    private String commandFormat;

    CommandTypeEnum(String commandTrigger, String usage, String commandFormat) {
        this.commandTrigger = commandTrigger;
        this.usage = usage;
        this.commandFormat = commandFormat;
    }

    /**
     * Identifies the correct CommandTypeEnum depending on user input.
     *
     * @param userInput User input.
     * @return The appropriate CommandTypeEnum.
     * @throws UnknownCommandException If user input has no valid command type.
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
     * Gets the length of the command trigger string.
     *
     * @return length of the command trigger string.
     */
    public int getCommandTypeStringLength() {
        return commandTrigger.length();
    }

    /**
     * Gets the help text for a specific command.
     *
     * @return The help text.
     */
    public String getHelpTextForCommand() {
        return String.format("%s\nusage: %s\nformat: %s", this.commandTrigger, this.usage, this.commandFormat);
    }
}
