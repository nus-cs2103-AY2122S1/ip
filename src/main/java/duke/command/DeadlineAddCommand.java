package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DeadlineAddCommand extends AddCommand {
    private String description;
    private String time;

    public DeadlineAddCommand(String description, String time) {
        this.description = description;
        this.time = time;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        int totalTasks = taskList.addToList("D", description, time);
        storage.addToText("D", description, time);
        ui.addingTask(totalTasks, description, time, "D");
    }
}
