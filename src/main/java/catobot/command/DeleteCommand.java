package catobot.command;

import catobot.Storage;
import catobot.Ui;
import catobot.exception.BotException;
import catobot.exception.InvalidCommandException;
import catobot.item.TaskList;

public class DeleteCommand extends Command {

    private String content;

    protected DeleteCommand(String content) {
        this.content = content;
    }

    /**
     * Displays the list of tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        try {
            int index = Integer.parseInt(content.substring("delete".length()).trim());
            Ui.respond(tasks.deleteTask(index));
        } catch(NumberFormatException e) {
            throw new InvalidCommandException();
        }
    }

}
