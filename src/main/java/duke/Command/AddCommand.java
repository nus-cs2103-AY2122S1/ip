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

public class AddCommand extends Command {
    private Task task;

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

    @Override
    public void runCommand(TaskList taskList, Ui ui, Storage storage) {
        ui.taskAdded(task);
        taskList.addTask(task);
        ui.showTaskListSize(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
