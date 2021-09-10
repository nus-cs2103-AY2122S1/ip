package command;

import message.Message;
import tasklist.TaskList;
import type.CommandTypeEnum;

/**
 * Encapsulates a help command.
 */
public class HelpCommand extends Command {
    /**
     * Creates a `HelpCommand`.
     *
     * @return `HelpCommand`.
     */
    public static HelpCommand createCommand() {
        return new HelpCommand();
    }

    /**
     * Executes a `HelpCommand`.
     *
     * @return Message representing the command is executed.
     * @param list `TaskList` containing all tasks.
     */
    @Override
    public Message execute(TaskList list) {
        return this.getOutputMessage();
    }

    public Message getOutputMessage() {
        String prefix = "These are the available commands:";

        StringBuilder stringBuilder = new StringBuilder();
        CommandTypeEnum[] commandTypes = CommandTypeEnum.values();

        for (int i = 0; i < commandTypes.length; i++) {
            stringBuilder.append(String.format("%d. %s\n", i + 1, commandTypes[i].toFullInfoString()));
        }

        return new Message(prefix, stringBuilder.toString());
    }
}
