package Yoyo.command;

import Yoyo.core.Storage;
import Yoyo.task.TaskList;
import Yoyo.core.Ui;

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
