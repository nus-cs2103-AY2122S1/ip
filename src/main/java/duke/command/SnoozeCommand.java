package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * This class handles command that snooze a task to a given date.
 */
public class SnoozeCommand extends Command {
    public static final String COMMAND_WORD = "snooze";
    private final String des;

    /**
     * Constructor for SnoozeCommand.
     * @param des Task number and new date.
     */
    public SnoozeCommand(String des) {
        this.des = des;
    }

    /**
     * Method to execute command.
     *
     * @param tasks The list of tasks in the current programme.
     * @param ui The user interface.
     * @param storage Handles interaction with the file.
     * @return Response of postponing.
     * @throws DukeException All exceptions related to Duke.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int taskNum;
        LocalDate newDate;
        String result;
        String stripped = des.strip();
        String[] info = stripped.split(" "); // example is "1 2021-09-25" -> ["1","2021-09-25"]
        if (!(info.length == 2)) {
            throw new InvalidCommandException();
        }

        try {
            taskNum = Integer.parseInt(info[0]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("The first number should be an int.");
        }

        try {
            newDate = LocalDate.parse(info[1]);
        } catch (DateTimeParseException e) {
            throw new DukeException("Wrong date time format! Please use yyyy-MM-dd.");
        }

        Task item = tasks.get(taskNum);
        String description = item.getDescription();
        if (item instanceof Todo) {
            throw new DukeException("Todo tasks cannot be snoozed.");
        } else if (item instanceof Event) {
            tasks.delete(taskNum);
            result = tasks.add(new Event(description, newDate));
        } else {
            tasks.delete(taskNum);
            result = tasks.add(new Deadline(description, newDate));
        }

        return result;
    }
}
