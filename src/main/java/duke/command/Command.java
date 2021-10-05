package duke.command;

import duke.commandresult.CommandResult;
import duke.exception.DukeException;
import duke.tasklist.TaskList;

/**
 * Application functionality is implemented by creating classes that extends Command. These classes override
 * execute to achieve its specific task.
 */
public abstract class Command implements ListNumberPrintable {

    private final TaskList taskList;

    /**
     * Abstract constructor that will have to be implemented by all classes that extend Command.
     * @param taskList that is passed by Duke.
     */
    protected Command(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Abstract method that will have to be implemented by all classes that extend Comand.
     * @return CommandResult.
     */
    public abstract CommandResult execute() throws DukeException;

    /**
     * Getter that retrieves the TaskList that is set when calling the constructor.
     * @return TaskList of the command.
     */
    public TaskList getTaskList() {
        return this.taskList;
    }

    /**
     * Overrides the printListNumber method as indicated by ListNumberPrintable.
     * @return String that will be passed to UserInterface to be rendered.
     */
    @Override
    public String printListNumber(TaskList taskList) {
        return "You now have "
                + taskList.size() + " tasks in the list.";
    }

}

