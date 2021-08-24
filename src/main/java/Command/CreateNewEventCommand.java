package Command;

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
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String[] message_and_eventDate = super.getExtraInput().split("/at ");
        taskList.add(new Event(message_and_eventDate[0], message_and_eventDate[1]));
    }
}
