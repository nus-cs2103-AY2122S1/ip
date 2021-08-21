package aisu.command;

import aisu.*;
import aisu.task.Task;

public class MarkDoneCommand extends Command {
    private final int parseInt;

    public MarkDoneCommand(int parseInt) {
        this.parseInt = parseInt;
    }

    @Override
    public void execute(TaskList tasklist, Storage storage, Ui ui) throws AisuException {
        Task completedTask = tasklist.markDone(this.parseInt);
        storage.save(tasklist);
        ui.showToUser(" Nice! I've marked this task as completed:\n" + completedTask);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}