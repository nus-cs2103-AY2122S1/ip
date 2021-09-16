package ponyo.commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import ponyo.common.Messages;
import ponyo.data.exceptions.PonyoException;
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
        case "t":
            try {
                return addTodo(tasks, storage);
            } catch (ArrayIndexOutOfBoundsException e) {
                return Ui.show(Messages.MESSAGE_INVALID_TODO);
            }
        case "deadline":
        case "d":
            try {
                return addDeadline(tasks, storage);
            } catch (ArrayIndexOutOfBoundsException e) {
                return Ui.show(Messages.MESSAGE_INVALID_DEADLINE);
            } catch (DateTimeParseException e) {
                return Ui.show(Messages.MESSAGE_INVALID_DATE_FORMAT);
            }
        case "event":
        case "e":
            try {
                return addEvent(tasks, storage);
            } catch (ArrayIndexOutOfBoundsException e) {
                return Ui.show(Messages.MESSAGE_INVALID_EVENT);
            } catch (DateTimeParseException e) {
                return Ui.show(Messages.MESSAGE_INVALID_DATE_FORMAT);
            }
        default:
            throw new PonyoException(Messages.MESSAGE_INVALID);
        }
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }

    /**
     * Handles the creation of a new todo.
     *
     * @param tasks a TaskList of tasks objects
     * @param storage a Storage object to interact with hard disk
     * @return String array of todo components
     */
    private String[] addTodo(TaskList tasks, Storage storage) {
        Task t = new Todo(taskToAdd[1]);

        tasks.add(t);
        storage.writeToFile(t);
        return formatTask(t, tasks.size());
    }

    /**
     * Handles the creation of a new Deadline.
     *
     * @param tasks a TaskList of tasks objects
     * @param storage a Storage object to interact with hard disk
     * @return String array of deadline components
     */
    private String[] addDeadline(TaskList tasks, Storage storage) {
        Task t = new Deadline(getTaskDetails()[0], getTaskDetails()[1]);

        tasks.add(t);
        storage.writeToFile(t);
        return formatTask(t, tasks.size());
    }

    /**
     * Handles the creation of a new Event.
     *
     * @param tasks a TaskList of tasks objects
     * @param storage a Storage object to interact with hard disk
     * @return String array of event components
     */
    private String[] addEvent(TaskList tasks, Storage storage) {
        Task t = new Event(getTaskDetails()[0], getTaskDetails()[1]);

        tasks.add(t);
        storage.writeToFile(t);
        return formatTask(t, tasks.size());
    }

    /**
     * Gets details of a newly created task.
     *
     * @return String array task details: description, by-date etc.
     */
    private String[] getTaskDetails() {
        int slashIndex = taskToAdd[1].indexOf("/");
        assert slashIndex >= 0;
        String desc = taskToAdd[1].substring(0, slashIndex);
        String date = formatDate(taskToAdd[1], slashIndex);

        return new String[] {desc, date};
    }

    /**
     * Formats the task into printable format.
     *
     * @param task the task to be printed
     * @param size size of the new taskList
     * @return a String representation to print
     */
    private static String[] formatTask(Task task, int size) {
        return Ui.show("Got it. I've added this task: \n\t" + task,
                "\nNow you have " + size + " tasks in the list.");
    }

    /**
     * Formats the date from yyyy-mm-dd to MM d yyyy.
     *
     * @param inputDate The original date in the form of yyyy-mm-dd
     * @param slashIndex The index to split the command to get the date
     * @return The string representation of the date in MMM d yyyy
     * @throws DateTimeParseException if the inputDate is of wrong format
     */
    private String formatDate(String inputDate, int slashIndex) throws DateTimeParseException {
        LocalDate date = LocalDate.parse(inputDate.substring(slashIndex + 4));
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
