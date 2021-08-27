package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        try {
            String taskString = taskList.deleteTask(index);
            storage.rewrite(taskList);
            ui.showDeleteMessage(taskString, taskList);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid task number!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
