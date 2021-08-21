package command;

import tasklist.Task;
import tasklist.TaskList;
import ui.message.DoneMessage;
import exception.ErrorAccessingFile;
import exception.InvalidTaskNumberException;
import exception.MissingCommandDescriptionException;
import exception.NonExistentTaskNumberException;
import type.DukeCommandTypeEnum;

public class DoneCommand extends Command {
    private int taskNumber;
    private Task task;

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public static DoneCommand createCommand(String description)
            throws InvalidTaskNumberException, MissingCommandDescriptionException {
        // Validate before creating the action
        Command.validateDescriptionNotEmpty(DukeCommandTypeEnum.DONE, description);

        return new DoneCommand(Command.getTaskNumberFromMessage(description));
    }

    public void execute(TaskList list) throws NonExistentTaskNumberException, ErrorAccessingFile {
        this.task = list.markTaskAsDone(taskNumber);
    }

    public DoneMessage getOutputMessage() {
        return new DoneMessage(task.toString());
    }
}
