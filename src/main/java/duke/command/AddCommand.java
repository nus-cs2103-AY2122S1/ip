package duke.command;

import duke.exception.IncompleteDescriptionException;
import duke.exception.InvalidDateFormatException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * A class that handles task-addition command.
 */
public class AddCommand extends Command{

    private enum TaskType {
        TODO, EVENT, DEADLINE
    }

    private final String taskType;
    private final String taskDescriptions;

    /**
     * Constructs an AddCommand instance that handles the logic of task-addition.
     *
     * @param taskType The type of the to-be-added task.
     * @param taskDescriptions The description of the to-be-added task.
     */
    public AddCommand(String taskType, String taskDescriptions) {
        this.taskType = taskType;
        this.taskDescriptions = taskDescriptions;
    }

    private String[] validateCommand() throws IncompleteDescriptionException, InvalidDateFormatException {
        // The descriptions of the task splits into two components.
        String[] descriptionComponents = taskDescriptions.split(" /by | /at ", 2);

        // The messages used to alert the user for unexpected situations.
        String descriptionMessage = "The description of a %s is incomplete.";
        String dateMessage = "Please specify the date in yyyy-mm-dd format!";

        if (taskType.equals("TODO")) {
            return descriptionComponents;
        }

        // Checks whether the command consists of 2 parts and if the date is specified in the command.
        boolean isShortDescription = descriptionComponents.length < 2;
        boolean isIncompleteDescription = isShortDescription || descriptionComponents[1].trim().isEmpty();

        if (isIncompleteDescription) {
            throw new IncompleteDescriptionException(String.format(descriptionMessage, taskType.toLowerCase()));
        }

        // Checks if the date specified match the format.
        boolean matchDateFormat = descriptionComponents[1].matches("\\d{4}-\\d{2}-\\d{2}");

        if (matchDateFormat) {
            return descriptionComponents;
        } else {
            throw new InvalidDateFormatException(dateMessage);
        }

    }

    private Task createTask(String description, LocalDate date) {
        switch (TaskType.valueOf(taskType)) {
        case TODO:
            return new Todo(description);
        case EVENT:
            return new Event(description, date);
        case DEADLINE:
            return new Deadline(description, date);
        default:
            return null;
        }
    }

    /**
     * Returns the response after executing the task-addition command.
     *
     * @param tasks The list that stores all the tasks to be added/deleted.
     * @param ui The ui that deals with interactions with the user.
     * @param storage The storage that deals with loading tasks from the file and saving tasks in the file.
     * @throws IncompleteDescriptionException The exception for handling command with incomplete description.
     * @throws InvalidDateFormatException The exception for handling command with invalid date format.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IncompleteDescriptionException,
            InvalidDateFormatException {
        try {
            // The components of the command that specifies the task's details.
            String[] taskComponents = validateCommand();
            String description = taskComponents[0];
            LocalDate date = taskType.equals("TODO") ? null : LocalDate.parse(taskComponents[1]);

            // Create task, then add to the list and save it.
            Task task = createTask(description, date);
            tasks.add(task);
            storage.save(tasks);

            return String.format("%s%s",
                    "Got it. I've added this task:\n\t "
                    + task,
                    "\nNow you have "
                    + tasks.getTaskNum()
                    + " tasks in the list.");
        } catch (DateTimeParseException e) {
            return new Ui().showError(e.getMessage());
        }
    }

    /**
     * Returns the boolean false since it is not a command that exits the program.
     *
     * @return The boolean false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
