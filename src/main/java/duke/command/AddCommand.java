package duke.command;

import duke.exception.DukeException;

import duke.taskTypes.Task;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class AddCommand extends Command {

    private final String taskDetails;
    private final String addType;

    public AddCommand(Storage storage, TaskList taskList, Ui ui, String taskDetails, String addType) {
        super(storage, taskList, ui);
        this.taskDetails = taskDetails;
        this.addType = addType;
    }

    public boolean exec() throws DukeException {
        switch (addType) {
        case "deadline":
            Task deadline = taskList.deadline(taskDetails);
            ui.taskAdded(deadline, taskList);
            storage.saveAdded(deadline);
            break;
        case "todo":
            Task todo = taskList.todo(taskDetails);
            ui.taskAdded(todo, taskList);
            storage.saveAdded(todo);
            break;
        case "event":
            Task event = taskList.event(taskDetails);
            ui.taskAdded(event, taskList);
            storage.saveAdded(event);
            break;
        }
        return true;
    }
}
