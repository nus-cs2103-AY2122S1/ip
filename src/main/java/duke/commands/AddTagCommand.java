package duke.commands;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.data.tasks.Task;
import duke.storage.Storage;
import duke.ui.Ui;

public class AddTagCommand extends Command {
    private final int taskId;
    private final String tag;

    public AddTagCommand(String userInput) {
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

        Task addTo = tasks.get(this.taskId - 1);
        addTo.addTag(this.tag);
        ui.printMsg(String.format("Added #%s:\n    %s", this.tag, addTo));
        storage.write(tasks.getSaveData());
    }
}
