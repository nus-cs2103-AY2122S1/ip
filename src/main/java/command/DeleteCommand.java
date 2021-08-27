package command;

import exception.ErrorAccessingFile;
import exception.InvalidTaskNumberException;
import exception.MissingCommandDescriptionException;
import exception.NonExistentTaskNumberException;
import tasklist.Task;
import tasklist.TaskList;
import type.DukeCommandTypeEnum;
import ui.message.DeleteMessage;

/**
 * Encapsulates a delete command after it is parsed from the user input.
 */
public class DeleteCommand extends Command {
    private int taskNumber;
    private Task task;
    private TaskList list;

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
        // Validate before creating the action
        Command.validateDescriptionNotEmpty(DukeCommandTypeEnum.DELETE, description);

        return new DeleteCommand(Command.getTaskNumberFromMessage(description));
    }

    /**
     * Executes a `DeleteCommand` by deleting a task from the list.
     *
     * @param list `TaskList` containing all tasks.
     * @throws NonExistentTaskNumberException If the task number does not exist in the list.
     * @throws ErrorAccessingFile If there is an error accessing the storage file.
     */
    public void execute(TaskList list) throws NonExistentTaskNumberException, ErrorAccessingFile {
        this.task = list.deleteTaskFromList(this.taskNumber);
        this.list = list;
    }

    /**
     * Gets the output message representing the command is executed.
     *
     * @return `DeleteMessage`.
     */
    public DeleteMessage getOutputMessage() {
        return new DeleteMessage(task.toString(), list.getNumberOfTasks());
    }
}
