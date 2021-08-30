package kermit.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import kermit.KermitException;
import kermit.Storage;
import kermit.TaskList;
import kermit.Ui;
import kermit.tasks.Deadline;
import kermit.tasks.Event;
import kermit.tasks.Task;
import kermit.tasks.ToDo;

/**
 * AddTask command creates and adds task to task list.
 */
public class AddTaskCommand extends Command {
    private Task task;

    /**
     * AddTasks command constructor.
     * Can construct commands for "todo", "deadline" and "event".
     *
     * @param taskType String of task type to construct command.
     * @param description Description of task.
     * @param flag Additional data associated with command e.g date.
     * @throws KermitException if unable to parse date (if required).
     */
    public AddTaskCommand(String taskType, String description, String flag) throws KermitException {
        if (description.equals("")) {
            throw new KermitException("The argument of the " + taskType + " command cannot be empty!");
        }

        if (taskType.equals("todo")) {
            this.task = new ToDo(description);
        } else {
            try {
                LocalDate date = parseDate(flag);
                switch (taskType) {
                case "deadline":
                    task = new Deadline(description, date);
                    break;
                case "event":
                    task = new Event(description, date);
                    break;
                default:
                    throw new KermitException("Invalid task type!");
                }
            } catch (DateTimeParseException e) {
                throw new KermitException("That is an invalid date!");
            }
        }
    }

    /**
     * Parse dates in form dd-mm-yyyy to yyyy-mm-dd, the format LocalDate is compatible with.
     *
     * @param dateString  date string in form dd-mm-yyyy.
     * @return LocalDate object.
     * @throws KermitException if unable to parse string to date.
     */
    private static LocalDate parseDate(String dateString) throws KermitException {
        String[] components = dateString.split("-");
        try {
            String day = components[0];
            String month = components[1];
            String year = components[2];
            LocalDate parsedDate = LocalDate.parse(String.join("-", year, month, day));
            return parsedDate;
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw new KermitException("That is an invalid date!");
        }
    }

    /**
     * Executes add task command.
     * Adds task to task list, notify user and save task list.
     *
     * @param taskList Instance of task list used.
     * @param ui       Instance of Ui used.
     * @param storage  Instance of storage class used.
     * @throws KermitException if task could not be saved.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws KermitException {
        taskList.add(task);
        ui.showAddTaskMessage(task, taskList);
        storage.save(taskList);
    }

    /**
     * Returns if command is the exit command.
     *
     * @return Always returns false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
