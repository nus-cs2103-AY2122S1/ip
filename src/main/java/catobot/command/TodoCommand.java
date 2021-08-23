package catobot.command;

import catobot.Storage;
import catobot.Ui;
import catobot.exception.BotException;
import catobot.item.TaskList;
import catobot.item.Todo;

public class TodoCommand extends Command {
    private String content;

    protected TodoCommand(String content) {
        this.content = content;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        String description = content.substring("todo".length()).trim();
        Ui.respond(tasks.add(Todo.of(description)));
    }

}
