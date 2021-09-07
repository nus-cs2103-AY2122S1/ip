package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import tasks.Deadline;

/**
 * Command to create a new Deadline task.
 *
 * @author Quan Teng Foong
 */
public class CreateNewDeadlineCommand extends Command {

    public CreateNewDeadlineCommand(String message) {
        super(message);
    }

    /**
     * Adds a new Deadline task to the task list.
     *
     * @param taskList the current list of tasks
     * @param ui the user interface object
     * @param storage the storage object
     * @return acknowledgement that Deadline has been created
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String[] messageAndEndTime = super.getExtraInput().split("/by ");
        return taskList.add(new Deadline(messageAndEndTime[0], messageAndEndTime[1]));
    }

    /**
     * Overrides equals method. To be used for JUnit testing.
     *
     * @param o other object for checking
     * @return true if this instance and o are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof CreateNewDeadlineCommand) {
            // Compares the 2 extraInputs
            String oExtraInput = ((CreateNewDeadlineCommand) o).getExtraInput();
            return super.getExtraInput().equals(oExtraInput);
        }
        return false;
    }
}
