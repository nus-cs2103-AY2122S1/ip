package duke.command;

import duke.DukeUi;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Represents a command to add a ToDo task.
 */
public class CommandTodo extends DukeCommand {
    private ToDo task;

    /**
     * Creates a new CommandTodo command.
     *
     * @param t Task that is to be added.
     */
    public CommandTodo(ToDo t) {
        this.task = t;
    }

    /**
     * Adds the task to the task list.
     *
     * @param tl Task list for the user.
     */
    @Override
    public void execute(TaskList tl) {
        DukeUi.printLine(tl.addTask(task));
    };

    /**
     * As described in DukeCommand.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    };
}
