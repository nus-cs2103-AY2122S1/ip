package catobot.command;

import catobot.Storage;
import catobot.exception.BotException;
import catobot.item.TaskList;

/**
 * Represents the command to search a key word.
 */
public class FindCommand extends Command {

    private final String keyword;

    /**
     * Constructor for find command.
     *
     * @param content The content of the command.
     */
    protected FindCommand(String content) {
        this.keyword = Parser.parseSingleArgument(content, CommandType.FIND);
    }

    /**
     * Finds tasks.
     *
     * @param tasks The list of tasks to be worked on.
     * @param storage The storage of the tasks.
     * @return The result of the search.
     * @throws BotException If the command is not valid.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws BotException {
        return tasks.search(keyword);
    }

}
