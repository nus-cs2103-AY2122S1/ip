package command;

import exception.MissingCommandDescriptionException;
import tasklist.TaskList;
import type.DukeCommandTypeEnum;
import ui.message.ListMessage;

/**
 * Encapsulates a done command after it is parsed from user input.
 */
public class FindCommand extends Command {
    private String keyword;
    private TaskList list;

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
        Command.validateDescriptionNotEmpty(DukeCommandTypeEnum.FIND, keyword);

        return new FindCommand(keyword);
    }

    /**
     * Executes a `DoneCommand` to return a list with tasks that match the keyword.
     *
     * @param list List containing matching tasks.
     */
    public void execute(TaskList list) {
        this.list = list.getListContainingKeyword(this.keyword);
    }

    /**
     * Gets the output message representing the command is executed.
     *
     * @return `ListMessage`.
     */
    public ListMessage getOutputMessage() {
        return new ListMessage("Here are the matching tasks in your list:", this.list.toString());
    }
}
