package command;

import catobot.Storage;
import catobot.Ui;
import exception.BotException;
import exception.EmptyCommandException;
import exception.InvalidCommandException;
import item.Deadline;
import item.TaskList;
import item.Todo;

import java.time.LocalDate;

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
