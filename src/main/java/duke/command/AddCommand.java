package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.task.Deadline;
import duke.task.Event;
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
            Todo newTodo = new Todo(description);
            tasks.addTask(newTodo);
            reply = "Got it. I've added this task:\n" + "  " + newTodo
                    + System.lineSeparator() + "Now you have " + tasks.getSize() + " tasks in the list.";
            storage.update(tasks);
            break;
        case "deadline":
            assert description.equals("/by");
            try {
                String[] parsedAdd = description.split(" /by ", 2);
                LocalDate date = LocalDate.parse(parsedAdd[1]);
                Deadline newDeadline = new Deadline(parsedAdd[0], date);
                tasks.addTask(newDeadline);
                reply = "Got it. I've added this task:\n" + "  " + newDeadline
                        + System.lineSeparator() + "Now you have " + tasks.getSize() + " tasks in the list.";
                storage.update(tasks);
                break;
            } catch (DateTimeParseException e) {
                throw new DukeException("Please use the specified date format (YYYY-MM-DD)!");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Please specify a description for this deadline.");
            }
        case "event":
            assert description.equals("/at");
            try {
                String[] parsedAdd = description.split(" /at ", 2);
                LocalDate date = LocalDate.parse(parsedAdd[1]);
                Event newEvent = new Event(parsedAdd[0], date);
                tasks.addTask(newEvent);
                reply = "Got it. I've added this task:\n" + "  " + newEvent
                        + System.lineSeparator() + "Now you have " + tasks.getSize() + " tasks in the list.";
                storage.update(tasks);
                break;
            } catch (DateTimeParseException e) {
                throw new DukeException("Please use the specified date format (YYYY-MM-DD)!");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Please specify a description for this event.");
            }
        default:
            throw new AssertionError(taskType);
        }
        return reply;
    }
}
