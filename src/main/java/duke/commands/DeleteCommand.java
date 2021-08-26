package duke.commands;

import duke.data.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

public class DeleteCommand extends Command {
    private final int taskNumber;

    public DeleteCommand(String userInput) {
        this.taskNumber = Integer.parseInt(
                userInput.replaceAll(
                        "[^0-9]",
                        ""));
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMsg(tasks.deleteTask(this.taskNumber));
        storage.write(tasks.getSaveData());
    }
}
