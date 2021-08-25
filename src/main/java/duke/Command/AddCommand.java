package duke.Command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.format.DateTimeParseException;

/**
 * Represents user command to add a task to the TaskList.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Class constructor.
     * @param input The user input to indicate the task to add.
     * @throws DukeException If the command format is invalid.
     */
    public AddCommand(String input) throws DukeException {
        if (input.toUpperCase().startsWith("TODO")) {
            try {
                String task = input.substring(5);
                this.task = new Todo(task);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("The description of a Todo cannot be empty!");
            }
        } else if (input.toUpperCase().startsWith("DEADLINE")) {
            try {
                String task = input.substring(9);
                int split = task.indexOf("/by");
                String description = task.substring(0, split);
                String by = task.substring(split + 4);
                this.task = new Deadline(description, by);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Invalid format. Please try again.\n");
            } catch (DateTimeParseException e) {
                throw new DukeException("Please enter the date with format [yyyy-mm-dd].\n");
            }
        } else if (input.toUpperCase().startsWith("EVENT")) {
            try {
                String task = input.substring(6);
                int split = task.indexOf("/at");
                String description = task.substring(0,split);
                String at = task.substring(split+4);
                this.task = new Event(description, at);
            } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                throw new DukeException("Invalid format. Please try again.\n");
            }  catch (DateTimeParseException e) {
                throw new DukeException("Please enter the date and time with format [yyyy-mm-dd HH:mm].\n");
            }
        }
    }

    /**
     * Executes the add command by adding the task to the TaskList then outputting the number of
     * current tasks in the TaskList.
     *
     * @param taskList A TaskList object that contains an arraylist of Task objects.
     * @param ui A Ui object that deals with interactions with the user.
     * @param storage A Storage object that loads and saves tasks in the file.
     */
    @Override
    public void runCommand(TaskList taskList, Ui ui, Storage storage) {
        ui.taskAdded(task);
        taskList.addTask(task);
        ui.showTaskListSize(taskList);
    }

    /**
     * Indicates if the command ends the program after executing.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
