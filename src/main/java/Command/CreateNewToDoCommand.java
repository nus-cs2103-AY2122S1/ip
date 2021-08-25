package Command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import tasks.ToDo;

/**
 * Command to create a new ToDo task.
 *
 * @author Quan Teng Foong
 */
public class CreateNewToDoCommand extends Command {

    public CreateNewToDoCommand(String message) {
        super(message);
    }

    /**
     * Adds a new ToDo task to the task list.
     *
     * @param taskList the current list of tasks
     * @param ui the user interface object
     * @param storage the storage object
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(new ToDo(super.getExtraInput()));
        ui.showAddedTask(taskList);
    }

    /**
     * Overrides equals method. To be used for JUnit testing.
     *
     * @param o other object for checking
     * @return true if this instance and o are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof CreateNewToDoCommand &&
                super.getExtraInput().equals(((CreateNewToDoCommand) o).getExtraInput());
    }
}
