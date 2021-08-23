package command;

import catobot.Storage;
import catobot.Ui;
import item.TaskList;

public class ListCommand extends Command {

    private String content;

    protected ListCommand() {}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.respond(tasks.display());
    }
}
