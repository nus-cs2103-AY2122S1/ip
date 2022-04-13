package duke.command;

import duke.Action;
import duke.DukeException;
import duke.Storage;
import duke.StringUtils;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

public class CloneCommand extends Command {
    private final int index;

    /**
     * Constructs a clone command using the given index.\
     *
     * @param index The given index.
     */
    public CloneCommand(int index) {
        super(Action.CLONE, false);
        this.index = index;
    }

    /**
     * Executes the command and prints the result in console.
     *
     * @param taskList The task list of duke.
     * @param storage  The local storage of duke.
     */
    @Override
    public void executeAndShow(TaskList taskList, Storage storage) {
        Ui.showMultiLines(execute(taskList, storage));
    }

    /**
     * Returns the result of executing the command.
     *
     * @param taskList The task list of duke.
     * @param storage  The local storage of duke.
     * @return A string representation of the result.
     * @throws DukeException When cloning a invalid task.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        Task target = taskList.getTask(index);
        Task temp;
        if (target instanceof Todo) {
            temp = new Todo(target.getDescription());
            taskList.addTask(temp);
        } else if (target instanceof Event) {
            Event e = (Event) target;
            temp = new Event(e.getDescription(), e.getEventTime());
            taskList.addTask(temp);
        } else if (target instanceof Deadline) {
            Deadline d = (Deadline) target;
            temp = new Deadline(d.getDescription(), d.getDeadlineTime());
            taskList.addTask(temp);
        } else {
            throw new DukeException("Clone a invalid task!");
        }
        return StringUtils.getAddTasKMessage(temp, taskList.getSize());
    }
}
