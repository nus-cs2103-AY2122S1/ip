package command;

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
     * @return acknowledgement that Deadline has been created
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(new ToDo(super.getExtraInput()));
        return "Alright, I've added the following task:\n"
                + "      " + taskList.get(taskList.size() - 1) + "\n      Now you have " + taskList.size()
                + " tasks in the list.\n";
    }

    /**
     * Overrides equals method. To be used for JUnit testing.
     *
     * @param o other object for checking
     * @return true if this instance and o are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof CreateNewToDoCommand
                && super.getExtraInput().equals(((CreateNewToDoCommand) o).getExtraInput());
    }
}
