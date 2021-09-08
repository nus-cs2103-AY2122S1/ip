package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exceptions.DukeException;
import tasks.Task;

/**
 * Command to create a new Event task.
 *
 * @author Quan Teng Foong
 */
public class CreateNewEventCommand extends Command {

    public CreateNewEventCommand(String message) {
        super(message);
    }

    /**
     * Adds a new Event task to the task list.
     *
     * @param taskList the current list of tasks
     * @param ui the user interface object
     * @param storage the storage object
     * @return acknowledgement that Deadline has been created
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String details = super.getExtraInput();
        return taskList.add(Task.createTask("event", details));
    }

    /**
     * Overrides equals method. To be used for JUnit testing.
     *
     * @param o other object for checking
     * @return true if this instance and o are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof CreateNewEventCommand) {
            // Compares the 2 extraInputs
            String oExtraInput = ((CreateNewEventCommand) o).getExtraInput();
            return super.getExtraInput().equals(oExtraInput);
        }
        return false;
    }
}
