package command;

import exception.ErrorAccessingFile;
import exception.InvalidDateTimeException;
import exception.InvalidTaskTimeFormatException;
import exception.InvalidTaskTypeException;
import exception.MissingCommandDescriptionException;
import tasklist.Task;
import tasklist.TaskList;
import type.DukeCommandTypeEnum;
import message.Message;

/**
 * Encapsulates an add command after it is parsed from the user input.
 */
public class AddCommand extends Command {
    private String description;
    private DukeCommandTypeEnum commandType;
    private Task task;
    private TaskList list;

    private AddCommand(String description, DukeCommandTypeEnum commandType) {
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
    public static AddCommand createCommand(String description, DukeCommandTypeEnum commandType)
            throws MissingCommandDescriptionException {
        // Validate before creating the action
        Command.validateDescriptionNotEmpty(commandType, description);

        return new AddCommand(description, commandType);
    }

    /**
     * Executes an `AddCommand` by adding a new task to the list.
     *
     * @param list `TaskList` containing all tasks.
     * @throws InvalidTaskTypeException If the task type is not valid.
     * @throws InvalidTaskTimeFormatException If a task meant to contain time information is not formatted properly.
     * @throws ErrorAccessingFile If there is an error accessing the storage file.
     * @throws InvalidDateTimeException If a task meant to contain time information has an invalid datetime format.
     */
    public void execute(TaskList list) throws
            InvalidTaskTypeException,
            InvalidTaskTimeFormatException,
            ErrorAccessingFile,
            InvalidDateTimeException {
        Task task = Task.createTask(this.description, this.commandType);
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
        String prefix = "Got it. I've added this task:";
        int numOfTasks = list.getNumberOfTasks();
        String taskWord = numOfTasks == 1 ? "task" : "tasks";
        String suffix = String.format("Now you have %d %s in the list", list.getNumberOfTasks(), taskWord);
        return new Message(prefix, task.toString(), suffix, "(＾＾)b");
    }
}
