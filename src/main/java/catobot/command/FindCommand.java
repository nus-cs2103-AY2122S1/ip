package catobot.command;

import catobot.Storage;
import catobot.Ui;
import catobot.exception.BotException;
import catobot.item.TaskList;

/**
 * Represents the command to search a key word.
 */
public class FindCommand extends Command {

    private final String content;

    protected FindCommand(String content) {
        this.content = content;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        String keyword = content.substring(CommandType.FIND.getValue().length()).trim();
        Ui.respond(tasks.search(keyword));
    }

}