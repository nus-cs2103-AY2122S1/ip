package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DeleteCommand implements Command{
    private final String indexString;

    public DeleteCommand(String indexString) {
        this.indexString = indexString;
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public void execute(TaskList t, Ui ui, Storage storage) {
        try {
            int index = Integer.parseInt(indexString) - 1;
            if (index >= t.getSize() || index <= -1) {
                throw new DukeException("invalid task");
            } else {
                Task deletedTask = t.remove(index);
                ui.textFrame("Quitter! I have deleted that task for you" + "\n" +
                        deletedTask + "\n" +
                        "Now you have " + t.getSize() + " tasks left.");
            }
        } catch (NumberFormatException | DukeException e) {
            ui.errorFrame(e.getMessage());
        }

    }
}
