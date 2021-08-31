package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.Storage;
import duke.ui.TaskList;
import duke.ui.Ui;

/**
 * AddCommand class to handle all the adding of events to the list.
 */
public class AddCommand extends Command {
    private final Task inputTask;

    /**
     * Constructor for AddCommand
     *
     * @param taskType Task type
     * @param details The name/date/time of the task
     * @throws DukeException Throws this if there are anything wrong with the inputs
     */
    public AddCommand(String taskType, String details) throws DukeException {
        super(true);
        String[] split;
        switch (taskType) {
        case "todo":
            this.inputTask = new Todo(details);
            break;

        case "deadline":
            if (details == null) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            split = details.split(" /by ");
            if (split.length <= 1) {
                throw new DukeException("☹ OOPS!!! Your deadline input format is not valid!");
            }
            this.inputTask = new Deadline(split[0].trim(), split[1]);
            break;

        case "event":
            if (details == null) {
                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            }
            split = details.split(" /at ");
            if (split.length <= 1) {
                throw new DukeException("☹ OOPS!!! Your event date input format is not valid!");
            }
            this.inputTask = new Event(split[0].trim(), split[1]);
            break;

        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Executes the AddCommand to update the list, update the txt, and print the returns to the user
     *
     * @param taskList The current list of tasks
     * @param ui       The current Ui
     * @param storage  The current storage class to handle the txt file
     * @throws IOException if the filepath has any issues
     */

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.add(this.inputTask);
        ui.showAddedMessage(taskList);
        Storage.updateText(taskList);
    }
}
