package duke.command;

import java.time.format.DateTimeParseException;

import duke.main.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents a duke.command to add a duke.task.
 *
 * @author Gordon Yit
 * @version CS2103T, Semester 2
 */
public class AddCommand extends Command {
    private String additionCommand;

    /**
     * Class constructor.
     *
     * @param additionCommand duke.command input by user to add a duke.task.
     */
    public AddCommand(String additionCommand) {
        this.additionCommand = additionCommand;
    }

    /**
     * Executes the duke.command to add a duke.task for Duke.Duke to keep track of.
     *
     * @param tasks   lists of tasks
     * @param ui      the user interface.
     * @param storage the storage file.
     * @throws DukeException exception handled by DukeException class
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task task;
            if (additionCommand.contains("deadline")) {
                task = new Deadline(additionCommand);
            } else if (additionCommand.contains("event")) {
                task = new Event(additionCommand);
            } else {
                task = new Todo(additionCommand);
            }

            Task taskAdded = tasks.add(task);
            ui.showTaskAdded(taskAdded, tasks.getNumTasks());
        } catch (StringIndexOutOfBoundsException | DateTimeParseException e) {
            throw new DukeException(e);
        }
    }
}
