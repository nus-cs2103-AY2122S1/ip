package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import tasks.Event;

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
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String[] messageAndEventDate = super.getExtraInput().split("/at ");
        taskList.add(new Event(messageAndEventDate[0], messageAndEventDate[1]));
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
        return o instanceof CreateNewEventCommand
                && super.getExtraInput().equals(((CreateNewEventCommand) o).getExtraInput());
    }
}
