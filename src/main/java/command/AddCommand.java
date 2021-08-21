package command;

import tasklist.Task;
import tasklist.TaskList;
import ui.message.AddMessage;
import exception.ErrorAccessingFile;
import exception.InvalidTaskTimeFormatException;
import exception.InvalidTaskTypeException;
import exception.MissingCommandDescriptionException;
import exception.InvalidDateTimeException;
import type.DukeCommandTypeEnum;

public class AddCommand extends Command {
    private String description;
    private DukeCommandTypeEnum actionType;
    private Task task;
    private TaskList list;

    public AddCommand(String description, DukeCommandTypeEnum actionType) {
        this.description = description;
        this.actionType = actionType;
    }

    public static AddCommand createCommand(String description, DukeCommandTypeEnum actionType)
            throws MissingCommandDescriptionException {
        // Validate before creating the action
        Command.validateDescriptionNotEmpty(actionType, description);

        return new AddCommand(description, actionType);
    }

    public void execute(TaskList list) throws InvalidTaskTypeException, InvalidTaskTimeFormatException, ErrorAccessingFile, InvalidDateTimeException {
        Task task = Task.createTask(this.description, this.actionType);
        list.addTaskToList(task);
        this.task = task;
        this.list = list;
    }

    public AddMessage getOutputMessage() {
        return new AddMessage(task.toString(), list.getNumberOfTasks());
    }
}
