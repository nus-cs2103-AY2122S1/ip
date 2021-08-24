package ponyo.commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import ponyo.data.task.Task;
import ponyo.data.task.TaskList;
import ponyo.data.task.Todo;
import ponyo.data.task.Deadline;
import ponyo.data.task.Event;
import ponyo.ui.Ui;
import ponyo.storage.Storage;

/**
 * Adds a new task to the task list.
 */
public class AddCommand extends Command {
    private final String[] taskToAdd;

    public AddCommand(String[] taskToAdd) {
        this.taskToAdd = taskToAdd;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        switch (taskToAdd[0]) {
            case "todo":
                try {
                    Task t = new Todo(taskToAdd[1]);
                    tasks.add(t);
                    storage.fileLineToWrite(t);
                    showTask(t, tasks.size(), ui);
                } catch (ArrayIndexOutOfBoundsException e){
                    ui.show("\t☹ OOPS!!! The description of a todo cannot be empty.");
                }
                break;
            case "deadline":
                try {
                    int slashIndex = taskToAdd[1].indexOf("/");
                    Task t = new Deadline(taskToAdd[1].substring(0, slashIndex), dateFormatter(taskToAdd[1], slashIndex));
                    tasks.add(t);
                    storage.fileLineToWrite(t);
                    showTask(t, tasks.size(), ui);
                } catch (ArrayIndexOutOfBoundsException e){
                    ui.show("\t☹ OOPS!!! The description of a deadline cannot be empty.");
                } catch (DateTimeParseException e) {
                    ui.show("\t☹ OOPS!!! Please use the date format: yyyy-mm-dd.");
                }
                break;
            case "event":
                try {
                    int slashIndex = taskToAdd[1].indexOf("/");
                    Task t = new Event(taskToAdd[1].substring(0, slashIndex), dateFormatter(taskToAdd[1], slashIndex));
                    tasks.add(t);
                    storage.fileLineToWrite(t);
                    showTask(t, tasks.size(), ui);
                } catch (ArrayIndexOutOfBoundsException e){
                    ui.show("\t☹ OOPS!!! The description of an event cannot be empty.");
                } catch (DateTimeParseException e) {
                    ui.show("\t☹ OOPS!!! Please use the date format: yyyy-mm-dd.");
                }
                break;
        }
    }

    /**
     * Outputs the message after a task has been added to the task list.
     *
     * @param task The task that is added.
     * @param size The size of the task list array.
     * @param ui The Ui object in charge of displaying information.
     */
    public static void showTask(Task task, int size, Ui ui) {
        ui.show("\tGot it. I've added this task:",
                "\t\t" + task,
                "\tNow you have " + size + " tasks in the list.");
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
