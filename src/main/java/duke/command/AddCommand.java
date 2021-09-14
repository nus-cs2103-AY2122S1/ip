package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.exception.InvalidDateTimeFormatException;
import duke.exception.NoDescriptionException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents a Command to add a Task to the TaskList.
 */
public class AddCommand extends Command {

    private String taskType;
    private String description;
    private String reply;

    /**
     * The constructor for an AddCommand object.
     *
     * @param taskType The given type of Task.
     * @param description The given description of Task.
     */
    public AddCommand(String taskType, String description) {
        this.taskType = taskType;
        this.description = description;
    }

    /**
     * Executes the Command to add a Task.
     *
     * @param tasks The given TaskList to be updated.
     * @param storage The given Storage to save changes to.
     * @return The response to the user.
     * @throws DukeException When the user input does not adhere to the format.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        switch(taskType) {
        case "todo":
            return handleTodo(tasks, storage);
        case "deadline":
            return handleDeadline(tasks, storage);
        case "event":
            return handleEvent(tasks, storage);
        default:
            throw new AssertionError(taskType);
        }
    }

    private static String createReply(Task task, int size) {
        return "Got it. I've added this task:\n" + "  " + task
                + System.lineSeparator() + "Now you have " + size + " tasks in the list.";
    }

    private String handleTodo(TaskList tasks, Storage storage) throws DukeException {
        Todo newTodo = new Todo(description);
        tasks.addTask(newTodo);
        reply = createReply(newTodo, tasks.getSize());
        storage.update(tasks);
        return reply;
    }

    private String handleDeadline(TaskList tasks, Storage storage) throws DukeException {
        try {
            String[] parsedAdd = description.split(" /by ", 2);
            LocalDate date = LocalDate.parse(parsedAdd[1]);
            Deadline newDeadline = new Deadline(parsedAdd[0], date);
            tasks.addTask(newDeadline);
            reply = createReply(newDeadline, tasks.getSize());
            storage.update(tasks);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeFormatException();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoDescriptionException(taskType);
        }

        return reply;
    }

    private String handleEvent(TaskList tasks, Storage storage) throws DukeException {
        try {
            String[] parsedAdd = description.split(" /at ", 2);
            LocalDate date = LocalDate.parse(parsedAdd[1]);
            Event newEvent = new Event(parsedAdd[0], date);
            tasks.addTask(newEvent);
            reply = createReply(newEvent, tasks.getSize());
            storage.update(tasks);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeFormatException();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoDescriptionException(taskType);
        }

        return reply;
    }
}
