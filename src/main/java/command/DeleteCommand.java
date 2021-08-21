package command;

import tasklist.Task;
import tasklist.TaskList;
import ui.message.DeleteMessage;
import exception.ErrorAccessingFile;
import exception.InvalidTaskNumberException;
import exception.MissingCommandDescriptionException;
import exception.NonExistentTaskNumberException;
import type.DukeCommandTypeEnum;

public class DeleteCommand extends Command {
    private int taskNumber;
    private Task task;
    private TaskList list;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public static DeleteCommand createCommand(String description) throws InvalidTaskNumberException, MissingCommandDescriptionException {
        // Validate before creating the action
        Command.validateDescriptionNotEmpty(DukeCommandTypeEnum.DELETE, description);

        return new DeleteCommand(Command.getTaskNumberFromMessage(description));
    }

    public void execute(TaskList list) throws NonExistentTaskNumberException, ErrorAccessingFile {
        this.task = list.deleteTaskFromList(this.taskNumber);
        this.list = list;
    }

    public DeleteMessage getOutputMessage() {
        return new DeleteMessage(task.toString(), list.getNumberOfTasks());
    }
}
