package workdone.command;

import workdone.data.Storage;
import workdone.data.TaskList;
import workdone.exception.InvalidTaskNoException;
import workdone.exception.UnableToUndoException;
import workdone.exception.WorkDoneException;

/**
 * Represents a command that undoes the previous command if possible. A subclass of the Command class.
 */
public class UndoCommand extends Command {
    /** Last command in the stack */
    private Command lastCommand;

    /**
     * Constructor of the class `UndoCommand`.
     *
     * @param lastCommand Last command in the stack.
     */
    public UndoCommand(Command lastCommand) {
        super("undo");
        this.lastCommand = lastCommand;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws WorkDoneException {
        if (this.lastCommand instanceof AddTaskCommand) {
            // delete the last task
            int index = tasks.getNumOfTasks() - 1;
            DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(index);
            try {
                deleteTaskCommand.execute(tasks, storage);
                this.message = deleteTaskCommand.message;
            } catch (InvalidTaskNoException e) {
                throw e;
            }
        } else if (this.lastCommand instanceof DeleteTaskCommand) {
            // add the task back
            AddTaskCommand addTaskCommand = new AddTaskCommand(this.lastCommand.task);
            addTaskCommand.execute(tasks, storage);
            this.message = addTaskCommand.message;
        } else if (this.lastCommand instanceof TaskDoneCommand) {
            // undone the task
            int index = ((TaskDoneCommand) this.lastCommand).getTaskIndex();
            TaskUndoneCommand taskUndoneCommand = new TaskUndoneCommand(index);
            taskUndoneCommand.execute(tasks, storage);
            this.message = taskUndoneCommand.message;
        } else if (this.lastCommand instanceof TaskUndoneCommand) {
            // mark the task as done
            int index = ((TaskUndoneCommand) this.lastCommand).getTaskIndex();
            TaskDoneCommand taskDoneCommand = new TaskDoneCommand(index);
            taskDoneCommand.execute(tasks, storage);
            this.message = taskDoneCommand.message;
        } else {
            // throw an error
            throw new UnableToUndoException(this.lastCommand);
        }
    }
}
