package duke.command;

import duke.Storage;
import duke.Ui;
import duke.tasks.TaskList;

public class DoneCommand extends Command {
    String taskNumber;
    public DoneCommand(String command) {
        this.taskNumber = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Integer count = Integer.valueOf(this.taskNumber);
        tasks.getTask(count - 1).markAsDone();
        ui.respondToDone(tasks.getTasks(), count);
        storage.rewriteFile(tasks.getTasks());
    }

    @Override
    public Boolean isExit() {
        return false;
    }
}
