package duke.command;

import duke.Storage;
import duke.Ui;
import duke.tasks.TaskList;

public class DeleteCommand extends Command {
    String taskNumber;
    public DeleteCommand(String command) {
        this.taskNumber = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Integer number = Integer.valueOf(this.taskNumber);
        ui.respondToDelete(tasks.getTasks(), number - 1);
        tasks.getTasks().remove(number - 1);
        storage.rewriteFile(tasks.getTasks());
    }

    @Override
    public Boolean isExit() {
        return false;
    }
}
