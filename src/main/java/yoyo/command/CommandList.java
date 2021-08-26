package yoyo.command;

import yoyo.core.Storage;
import yoyo.task.TaskList;
import yoyo.core.Ui;

public class CommandList extends Command {
    public CommandList(String[] inputTokens) {
        super(inputTokens);
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        ui.printTaskList(tasks);
    }
}
