package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class EventAddCommand extends AddCommand {
    private String description;
    private String time;

    public EventAddCommand(String description, String time) {
        this.description = description;
        this.time = time;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        int totalTasks = taskList.addToList("E", description, time);
        storage.addToText("E", description, time);
        ui.addingTask(totalTasks, description, time, "E");
    }
}
