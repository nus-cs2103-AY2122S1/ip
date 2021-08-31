package ponyo.commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import ponyo.data.task.Deadline;
import ponyo.data.task.Event;
import ponyo.data.task.Task;
import ponyo.data.task.TaskList;
import ponyo.data.task.Todo;
import ponyo.storage.Storage;
import ponyo.ui.Ui;

/**
 * Adds a new task to the task list.
 */
public class AddCommand extends Command {
    private final String[] taskToAdd;

    public AddCommand(String[] taskToAdd) {
        this.taskToAdd = taskToAdd;
    }

    @Override
    public String[] execute(TaskList tasks, Storage storage) {
        switch (taskToAdd[0]) {
        case "todo":
            try {
                Task t = new Todo(taskToAdd[1]);
                tasks.add(t);
                storage.fileLineToWrite(t);
                return showTask(t, tasks.size());
            } catch (ArrayIndexOutOfBoundsException e) {
                return Ui.show("☹ OOPS!!! The description of a todo cannot be empty.");
            }
        case "deadline":
            try {
                int slashIndex = taskToAdd[1].indexOf("/");
                Task t = new Deadline(taskToAdd[1].substring(0, slashIndex), dateFormatter(taskToAdd[1], slashIndex));
                tasks.add(t);
                storage.fileLineToWrite(t);
                return showTask(t, tasks.size());
            } catch (ArrayIndexOutOfBoundsException e) {
                return Ui.show("☹ OOPS!!! The description of a deadline cannot be empty.");
            } catch (DateTimeParseException e) {
                return Ui.show("☹ OOPS!!! Please use the date format: yyyy-mm-dd.");
            }
        case "event":
            try {
                int slashIndex = taskToAdd[1].indexOf("/");
                Task t = new Event(taskToAdd[1].substring(0, slashIndex), dateFormatter(taskToAdd[1], slashIndex));
                tasks.add(t);
                storage.fileLineToWrite(t);
                return showTask(t, tasks.size());
            } catch (ArrayIndexOutOfBoundsException e) {
                return Ui.show("☹ OOPS!!! The description of an event cannot be empty.");
            } catch (DateTimeParseException e) {
                return Ui.show("☹ OOPS!!! Please use the date format: yyyy-mm-dd.");
            }
        default:
            break;
        }
        return new String[0];
    }

    /**
     * Outputs the message after a task has been added to the task list.
     *
     * @param task The task that is added.
     * @param size The size of the task list array.
     */
    public static String[] showTask(Task task, int size) {
        return Ui.show("Got it. I've added this task: \n\t" + task,
                "\nNow you have " + size + " tasks in the list.");
    }

    /**
     * Formats the date from yyyy-mm-dd to MM d yyyy
     *
     * @param inputDate The original date in the form of yyyy-mm-dd
     * @param slashIndex The index to split the command to get the date
     * @return The string representation of the date in MMM d yyyy
     * @throws DateTimeParseException Thrown when the inputDate is of wrong format.
     */
    public String dateFormatter(String inputDate, int slashIndex) throws DateTimeParseException {
        LocalDate date = LocalDate.parse(inputDate.substring(slashIndex + 4));
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
