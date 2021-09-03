package command;

import exception.DuplicateTaskException;
import exception.ErrorAccessingFileException;
import exception.InvalidDateTimeException;
import exception.InvalidTaskTimeFormatException;
import exception.InvalidTaskTypeException;
import exception.MissingCommandDescriptionException;
import message.Message;
import tasklist.Task;
import tasklist.TaskList;
import type.CommandTypeEnum;

/**
 * Encapsulates an add command after it is parsed from the user input.
 */
public class AddCommand extends Command {
    private String description;
    private CommandTypeEnum commandType;
    private Task task;
    private TaskList list;

    private AddCommand(String description, CommandTypeEnum commandType) {
        this.description = description;
        this.commandType = commandType;
    }

    /**
     * Creates an `AddCommand`.
     *
     * @param description Description from the command excluding the command type.
     * @param commandType Command type of the command.
     * @return `AddCommand`.
     * @throws MissingCommandDescriptionException If description is empty.
     */
    public static AddCommand createCommand(String description, CommandTypeEnum commandType)
            throws MissingCommandDescriptionException {
        // Validate before creating the command
        Command.validateDescriptionNotEmpty(commandType, description);

        return new AddCommand(description, commandType);
    }

    /**
     * Executes an `AddCommand` by adding a new task to the list.
     *
     * @param list `TaskList` containing all tasks.
     * @throws InvalidTaskTypeException If the task type is not valid.
     * @throws InvalidTaskTimeFormatException If a task meant to contain time information is not formatted properly.
     * @throws ErrorAccessingFileException If there is an error accessing the storage file.
     * @throws InvalidDateTimeException If a task meant to contain time information has an invalid datetime format.
     */
    public void execute(TaskList list) throws
            InvalidTaskTypeException,
            InvalidTaskTimeFormatException,
            ErrorAccessingFileException,
            InvalidDateTimeException,
            DuplicateTaskException {
        Task task = Task.createTask(this.description, this.commandType);

        list.validateTaskNotDuplicate(task);
        list.addTaskToList(task);

        this.task = task;
        this.list = list;
    }

    /**
     * Gets the output message representing the command is executed.
     *
     * @return `Message`.
     */
    public Message getOutputMessage() {
        assert list != null : "task list should not be null";
        assert task != null : "task should not be null";

        String prefix = "Got it. I've added this task:";

        int numOfTasks = list.getNumberOfTasks();
        String taskWord = numOfTasks == 1 ? "task" : "tasks";
        String suffix = String.format("Now you have %d %s in the list", list.getNumberOfTasks(), taskWord);

        String kaomoji = "(＾＾)b";

        return new Message(prefix, task.toString(), suffix, kaomoji);
    }
}
