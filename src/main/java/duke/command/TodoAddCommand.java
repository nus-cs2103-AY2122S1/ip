package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Todo;

public class TodoAddCommand extends AddCommand {

    private String description;

    public TodoAddCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        int totalTasks = taskList.addToList("T", description, "NA");
        storage.addToText("T", description, "NA");
        ui.addingTask(totalTasks, description, "NA", "T");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TodoAddCommand)) {
            return false;
        }
        TodoAddCommand other = (TodoAddCommand) obj;
        return this.description.equals(other.description);
    }
}
