package duke.commands;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.data.tasks.Task;
import duke.storage.Storage;
import duke.ui.Ui;

public class RemoveTagCommand extends Command {
    private final int taskId;
    private final String tag;

    public RemoveTagCommand(String userInput) {
        assert userInput.split(" ").length > 2;
        String[] userInputs = userInput.split(" ", 3);
        this.taskId = Integer.parseInt(userInputs[1]);
        this.tag = userInputs[2];
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (taskId < 0 || taskId > tasks.size()) {
            throw new DukeException("There is no task " + this.taskId);
        }

        Task removeFrom = tasks.get(this.taskId - 1);
        if (!removeFrom.hasTag(this.tag)) {
            throw new DukeException("No such tag: " + this.tag);
        }
        removeFrom.removeTag(this.tag);
        ui.printMsg(String.format("Removed #%s:\n    %s", this.tag, removeFrom));
        storage.write(tasks.getSaveData());
    }
}
