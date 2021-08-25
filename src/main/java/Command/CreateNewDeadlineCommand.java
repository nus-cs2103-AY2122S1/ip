package Command;

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
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String[] message_and_endTime = super.getExtraInput().split("/by ");
        taskList.add(new Deadline(message_and_endTime[0], message_and_endTime[1]));
    }

    /**
     * Overrides equals method. To be used for JUnit testing.
     *
     * @param o other object for checking
     * @return true if this instance and o are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof CreateNewDeadlineCommand &&
                super.getExtraInput().equals(((CreateNewDeadlineCommand) o).getExtraInput());
    }
}
