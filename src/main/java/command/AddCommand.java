package command;

import exception.DuplicateTaskException;
import exception.ErrorAccessingFileException;
import exception.InvalidCommandFormatException;
import exception.InvalidDateTimeFormatException;
import exception.InvalidTaskTypeException;
import exception.InvalidTimePeriodException;
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
     * @return Message representing the command is executed.
     * @throws InvalidTaskTypeException If the task type is not valid.
     * @throws ErrorAccessingFileException If there is an error accessing the storage file.
     * @throws InvalidDateTimeFormatException If a task meant to contain datetime has an invalid datetime format.
     * @throws DuplicateTaskException If there is an attempt to add a duplicate task.
     * @throws InvalidCommandFormatException If the task has an incorrect format.
     * @throws InvalidTimePeriodException If the start time is after the end time.
     */
    public Message execute(TaskList list) throws
            InvalidTaskTypeException,
            ErrorAccessingFileException,
            InvalidDateTimeFormatException,
            DuplicateTaskException,
            InvalidCommandFormatException,
            InvalidTimePeriodException {
        Task task = addTask(list);
        Message message = getOutputMessage(list, task);
        return message;
    }

    private Task addTask(TaskList list) throws
            InvalidTaskTypeException,
            InvalidDateTimeFormatException,
            DuplicateTaskException,
            ErrorAccessingFileException,
            InvalidCommandFormatException,
            InvalidTimePeriodException {
        Task task = Task.createTask(this.description, this.commandType);

        list.validateTaskNotDuplicate(task);
        list.addTaskToList(task);

        return task;
    }

    private Message getOutputMessage(TaskList list, Task task) {
        assert list != null : "task list should not be null";
        assert task != null : "task should not be null";

        String prefix = "Got it. I've added this task:";

        int numOfTasks = list.getNumberOfTasks();
        String taskWord = numOfTasks == 1 ? "task" : "tasks";
        String suffix = String.format("Now you have %d %s in the entire list", list.getNumberOfTasks(), taskWord);

        return new Message(prefix, task.toString(), suffix);
    }
}
