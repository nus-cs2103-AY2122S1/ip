package yoyo.command;

import yoyo.core.Storage;
import yoyo.core.Ui;
import yoyo.task.TaskList;

public class CommandList extends Command {
    public CommandList(String[] inputTokens) {
        super(inputTokens);
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        int currListLength = tasks.size();
        ui.printTaskList(tasks);
    }
}
