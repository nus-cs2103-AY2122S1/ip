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

/** A class that handles task-addition command. */
public class AddCommand extends Command{

    private enum TaskType {
        TODO, EVENT, DEADLINE
    }

    private final String taskType;
    private final String taskDescriptions;

    /**
     * A constructor for class AddCommand.
     *
     * @param taskType The type of the to-be-added task.
     * @param taskDescriptions The description of the to-be-added task.
     */
    public AddCommand(String taskType, String taskDescriptions) {

        this.taskType = taskType;
        this.taskDescriptions = taskDescriptions;
    }

    private String[] validateCommand()
            throws IncompleteDescriptionException,
            InvalidDateFormatException {

        String[] descriptionComponents = this.taskDescriptions.split(" /by | /at ", 2);
        String descriptionMessage = "The description of a %s is incomplete.";
        String dateMessage = "Please specify the date in yyyy-mm-dd format!";

        if (this.taskType.equals("TODO")) {
            return descriptionComponents;
        }

        if (descriptionComponents.length < 2 || descriptionComponents[1].trim().isEmpty()) {
            throw new IncompleteDescriptionException(String.format(descriptionMessage, this.taskType.toLowerCase()));
        }

        if (!descriptionComponents[1].matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new InvalidDateFormatException(dateMessage);
        }

        return descriptionComponents;
    }

    private Task createTask(String description, LocalDate date) {

        switch (TaskType.valueOf(this.taskType)) {
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
     * Execute the task-addition command.
     *
     * @param tasks The list that stores all the tasks to be added/deleted.
     * @param ui The ui that deals with interactions with the user.
     * @param storage The storage that deals with loading tasks from the file and saving tasks in the file.
     * @throws IncompleteDescriptionException The exception for handling command with incomplete description.
     * @throws InvalidDateFormatException The exception for handling command with invalid date format.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws IncompleteDescriptionException,
            InvalidDateFormatException {
        try {
            String[] taskComponents = validateCommand();
            String description = taskComponents[0];
            LocalDate date = this.taskType.equals("TODO") ? null : LocalDate.parse(taskComponents[1]);

            Task task = createTask(description, date);
            tasks.add(task);
            storage.save(tasks);

            System.out.println("\tGot it. I've added this task:");
            System.out.println("\t " + task);
            System.out.println("\tNow you have " + tasks.getTaskNum() + " tasks in the list.");

        } catch (DateTimeParseException e) {
            new Ui().showError(e.getMessage());
        }
    }

    /**
     * Return a boolean value of whether it is a command that exit the program.
     *
     * @return The boolean value of whether it is a command that exit the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
