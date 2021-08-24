package Yoyo.command;

import Yoyo.core.Storage;
import Yoyo.task.TaskList;
import Yoyo.core.Ui;

public class CommandBye extends Command {

    public CommandBye(String[] inputTokens) {
        super(inputTokens);
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        storage.deposit(tasks);
        ui.sayGoodbye();
    }

    @Override
    public boolean shouldContinueProgram() {
        return false;
    }
}
