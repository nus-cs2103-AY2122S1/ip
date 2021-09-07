package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class UpdateCommand extends Command {
    private int index;
    private String label;

    /**
     * Constructor for UpdateCommand
     * @param index item in tasklist to be updated
     * @param label new label for the item in tasklist
     */
    public UpdateCommand(int index, String label) {
        this.index = index;
        this.label = label;
    }

    @Override
    public String execute(TaskList tasklist, Ui ui, Storage store) {
        tasklist.get(index).setLabel(label);
        Task after = tasklist.get(index);
        return ui.notifyUpdateComplete(after);
    }
}
