package duke.command;

import duke.exception.DukeException;

import duke.taskTypes.Task;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Command that contains details when adding a task
 */
public class AddCommand extends Command {

    private final String taskDetails;
    private final String addType;

    /**
     * Basic Constructor
     *
     * @param storage Storage object to save
     * @param taskList Tasklist to add task to
     * @param ui Ui to display msg
     * @param taskDetails User input
     * @param addType
     */
    public AddCommand(Storage storage, TaskList taskList, Ui ui, String taskDetails, String addType){
        super(storage, taskList, ui);
        this.taskDetails = taskDetails;
        this.addType = addType;
    }

    /**
     * Executes a set of instructions
     *
     * @return boolean To relay whether to continue the project
     * @throws DukeException
     */
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
