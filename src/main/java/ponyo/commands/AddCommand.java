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
            try {
                return addTodo(tasks, storage);
            } catch (ArrayIndexOutOfBoundsException e) {
                return Ui.show(Messages.MESSAGE_INVALID_TODO);
            }
        case "deadline":
            try {
                return addDeadline(tasks, storage);
            } catch (ArrayIndexOutOfBoundsException e) {
                return Ui.show(Messages.MESSAGE_INVALID_DEADLINE);
            } catch (DateTimeParseException e) {
                return Ui.show(Messages.MESSAGE_INVALID_DATE_FORMAT);
            }
        case "event":
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

    private String[] addTodo(TaskList tasks, Storage storage) {
        Task t = new Todo(taskToAdd[1]);

        tasks.add(t);
        storage.writeToFile(t);
        return formatTask(t, tasks.size());
    }

    private String[] addDeadline(TaskList tasks, Storage storage) {
        Task t = new Deadline(getTaskDetails()[0], getTaskDetails()[1]);

        tasks.add(t);
        storage.writeToFile(t);
        return formatTask(t, tasks.size());
    }

    private String[] addEvent(TaskList tasks, Storage storage) {
        Task t = new Event(getTaskDetails()[0], getTaskDetails()[1]);

        tasks.add(t);
        storage.writeToFile(t);
        return formatTask(t, tasks.size());
    }

    private String[] getTaskDetails() {
        int slashIndex = taskToAdd[1].indexOf("/");
        assert slashIndex >= 0;
        String desc = taskToAdd[1].substring(0, slashIndex);
        String date = formatDate(taskToAdd[1], slashIndex);

        return new String[] {desc, date};
    }

    private static String[] formatTask(Task task, int size) {
        return Ui.show("Got it. I've added this task: \n\t" + task,
                "\nNow you have " + size + " tasks in the list.");
    }

    private String formatDate(String inputDate, int slashIndex) throws DateTimeParseException {
        LocalDate date = LocalDate.parse(inputDate.substring(slashIndex + 4));
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
