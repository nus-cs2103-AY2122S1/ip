package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Task;

/**
 * Encapsulates the add deadline command.
 */
public class AddDeadlineCommand implements Command {
    private String deadLineDesc;
    private String deadLineDate;

    /**
     * Constructor for an AddDeadlineCommand instance.
     *
     * @param deadLineDesc Deadline description as entered by user.
     * @param deadLineDate Deadline date as entered by user.
     */
    public AddDeadlineCommand(String deadLineDesc, String deadLineDate) {
        this.deadLineDesc = deadLineDesc;
        this.deadLineDate = deadLineDate;
    }

    /**
     * Creates a new task from user's input and adds task to the given task list.
     * @param tasks TaskList instance which the new task is to be added to.
     * @param ui Duke's UI.
     * @return The String representation of Duke's response.
     * @throws DukeException For invalid inputs.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) throws DukeException {
        Task t = new Deadline(deadLineDesc, deadLineDate);
        tasks.add(t);
        return ui.formatDoneAddingTaskMsg(tasks, t);
    }

    /**
     * Indicates if the command is an exit command.
     *
     * @return If the command is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
