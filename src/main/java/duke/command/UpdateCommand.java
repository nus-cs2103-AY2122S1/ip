package duke.command;

import java.time.LocalDate;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class UpdateCommand extends Command {
    private int index;
    private String label;
    private LocalDate date;

    /**
     * Constructor for UpdateCommand with label
     * @param index item in tasklist to be updated
     * @param label new label for the item in tasklist
     */
    public UpdateCommand(int index, String label) {
        this.index = index;
        this.label = label;
    }

    /**
     * Constructor for UpdateCommand with date
     * @param index item in tasklist to be updated
     * @param date new date for the item in tasklist
     */
    public UpdateCommand(int index, LocalDate date) {
        this.index = index;
        this.date = date;
    }

    @Override
    public String execute(TaskList tasklist, Ui ui, Storage store) {
        assert (this.date != null || this.label != null);
        if (this.label != null) {
            tasklist.get(index).setLabel(label);
            Task after = tasklist.get(index);
            return ui.notifyUpdateComplete(after);
        } else {
            //guard clause; should not set date of todo
            if (tasklist.get(index).getType().equals("T")) {
                return ui.notifyUpdateError();
            }

            tasklist.get(index).setDate(date);
            Task after = tasklist.get(index);
            return ui.notifyUpdateComplete(after);
        }
    }
}
