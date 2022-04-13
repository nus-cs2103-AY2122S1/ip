package command;

import exception.ErrorAccessingFileException;
import exception.InvalidTaskNumberException;
import exception.MissingCommandDescriptionException;
import exception.NonExistentTaskNumberException;
import message.Message;
import tasklist.Task;
import tasklist.TaskList;
import type.CommandTypeEnum;

/**
 * Encapsulates a delete command after it is parsed from the user input.
 */
public class DeleteCommand extends Command {
    private int taskNumber;

    private DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Creates a `DeleteCommand`.
     *
     * @param description Description from the command excluding the command type.
     * @return `DeleteCommand`.
     * @throws InvalidTaskNumberException If task number in description is not a number.
     * @throws MissingCommandDescriptionException If description is empty.
     */
    public static DeleteCommand createCommand(String description) throws
            InvalidTaskNumberException,
            MissingCommandDescriptionException {
        // Validate before creating the command
        Command.validateDescriptionNotEmpty(CommandTypeEnum.DELETE, description);

        return new DeleteCommand(Command.getTaskNumberFromMessage(description));
    }

    /**
     * Executes a `DeleteCommand` by deleting a task from the list.
     *
     * @param list `TaskList` containing all tasks.
     * @return Message representing the command is executed.
     * @throws NonExistentTaskNumberException If the task number does not exist in the list.
     * @throws ErrorAccessingFileException If there is an error accessing the storage file.
     */
    public Message execute(TaskList list) throws NonExistentTaskNumberException, ErrorAccessingFileException {
        Task task = list.deleteTaskFromList(this.taskNumber);
        return getOutputMessage(list, task);
    }

    private Message getOutputMessage(TaskList list, Task task) {
        assert list != null : "task list should not be null";
        assert task != null : "task should not be null";

        String prefix = "Noted. I've removed this task:";

        int numOfTasks = list.getNumberOfTasks();
        String taskWord = numOfTasks == 1 ? "task" : "tasks";
        String suffix = String.format("Now you have %d %s in the entire list", list.getNumberOfTasks(), taskWord);

        return new Message(prefix, task.toString(), suffix);
    }
}
