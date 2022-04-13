package duke.commands;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.parser.UnableToParseException;
import duke.parser.cli.CliParser;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.InvalidTaskSelectedException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * This class encapsulates the update command.
 */
public class UpdateCommand extends Command {
    private final String updatedFields;
    private final int taskId;

    /**
     * Constructor for the update command class.
     *
     * @param taskId The task id of the task to be updated.
     * @param updatedFields The fields to be updated. This can include description, and/or the deadline/event time.
     */
    public UpdateCommand(int taskId, String updatedFields) {
        this.updatedFields = updatedFields;
        this.taskId = taskId;
    }

    /**
     * Updates the in memory task list and storage with the updated task. If the argument is empty for
     * the attribute, those task attributes will remain the same.
     *
     * @param tasks The task list.
     * @param ui The UI for duke.
     * @param storage The storage for duke.
     * @throws InvalidTaskSelectedException Thrown if the task id is invalid.
     * @throws IOException Thrown if there was an exception saving the updated task to storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws InvalidTaskSelectedException, IOException, UnableToParseException {
        Task oldTask = tasks.getTaskById(taskId);
        Task newTask = null;
        // update task according to its type
        if (oldTask instanceof Todo) {
            newTask = getUpdatedTodo((Todo) oldTask);
        } else if (oldTask instanceof Deadline) {
            newTask = getUpdatedDeadline((Deadline) oldTask);
        } else if (oldTask instanceof Event) {
            newTask = getUpdatedEvent((Event) oldTask);
        } else {
            assert false : "There should only be 3 types of tasks";
        }
        tasks.update(taskId, newTask);
        storage.update(taskId - 1, newTask);
        ui.printTaskUpdated(oldTask, newTask);
    }

    private Todo getUpdatedTodo(Todo oldTodo) {
        // here, updated fields can only be description
        String description = oldTodo.getDescription();
        if (!updatedFields.isEmpty()) {
            description = updatedFields;
        }

        Todo newTodo = new Todo(description);
        if (oldTodo.isDone()) {
            newTodo.markAsDone();
        }
        return newTodo;
    }

    private Deadline getUpdatedDeadline(Deadline oldDeadline) throws UnableToParseException {
        LocalDate by = oldDeadline.getBy();
        String description = oldDeadline.getDescription();
        // index 0 has updated description, and if applicable, index 1 has updated time
        String[] split = updatedFields.split(CliParser.DELIMITER_DEADLINE, 2);
        String newDescription = split[0].trim();

        if (!newDescription.isEmpty()) {
            description = newDescription;
        }
        // if the split string has length 2, then it means the user has input the /by argument
        if (split.length == 2) {
            String newDeadlineTime = split[1].trim();
            try {
                by = newDeadlineTime.isEmpty() ? by : LocalDate.parse(newDeadlineTime);
            } catch (DateTimeParseException e) {
                throw new UnableToParseException("date format: " + newDeadlineTime);
            }
        }
        Deadline newDeadline = new Deadline(description, by);
        if (oldDeadline.isDone()) {
            newDeadline.markAsDone();
        }
        return newDeadline;
    }

    private Event getUpdatedEvent(Event oldEvent) {
        String at = oldEvent.getAt();
        String description = oldEvent.getDescription();
        // index 0 has updated description, and if applicable, index 1 has updated time
        String[] split = updatedFields.split(CliParser.DELIMITER_EVENT, 2);
        String newDescription = split[0].trim();
        if (!newDescription.isEmpty()) {
            description = newDescription;
        }
        // if the split string has length 2, then it means the user has input the /at argument
        if (split.length == 2) {
            String newEventTime = split[1].trim();
            at = newEventTime.isEmpty() ? at : newEventTime;
        }
        Event newEvent = new Event(description, at);
        if (oldEvent.isDone()) {
            newEvent.markAsDone();
        }
        return newEvent;
    }
}
