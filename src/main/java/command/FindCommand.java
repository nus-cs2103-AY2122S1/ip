package command;

import exception.MissingCommandDescriptionException;
import message.Message;
import tasklist.TaskList;
import type.CommandTypeEnum;

/**
 * Encapsulates a find command after it is parsed from user input.
 */
public class FindCommand extends Command {
    private String keyword;

    private FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Creates a `FindCommand`.
     *
     * @param keyword Keyword to search with.
     * @return `FindCommand`.
     * @throws MissingCommandDescriptionException If description is empty.
     */
    public static FindCommand createCommand(String keyword) throws MissingCommandDescriptionException {
        // Validate before creating the command
        Command.validateDescriptionNotEmpty(CommandTypeEnum.FIND, keyword);

        return new FindCommand(keyword);
    }

    /**
     * Executes a `FindCommand` to return a list with tasks that match the keyword.
     *
     * @param list List containing matching tasks.
     * @return Message representing the command is executed.
     */
    public Message execute(TaskList list) {
        TaskList listContainingKeyword = list.getListContainingKeyword(this.keyword);
        return getOutputMessage(listContainingKeyword);
    }

    private Message getOutputMessage(TaskList list) {
        assert list != null : "task list should not be null";

        if (list.isActiveListEmpty()) {
            return new Message("There are no tasks that match the keyword in your list");
        }

        String prefix = "Here are the matching tasks in your list:";
        return new Message(prefix, list.toString());
    }
}
