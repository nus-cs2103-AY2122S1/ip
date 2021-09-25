package duke.command;

import duke.main.DukeException;
import duke.main.Storage;
import duke.main.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Represents a command to add a task.
 *
 * @author Gordon Yit
 * @version CS2103T, Semester 2
 */
public class AddCommand extends Command {
    private String addCommand;

    /**
     * Class constructor.
     *
     * @param addCommand the user inputed string to add a task.
     */
    public AddCommand(String addCommand) {
        super();
        this.addCommand = addCommand;
        assert !isExit() : "isExit should return false";

    }

    /**
     * Executes the command to add a task for Duke.Duke to keep track of.
     *
     * @param tasks lists of tasks
     * @param ui the user interface.
     * @param storage the storage file.
     * @throws DukeException exception handled by DukeException class
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String taskType = addCommand.split(" ")[0];
        Task task;
        try {
            System.out.println(taskType);
            switch (taskType) {
            case "deadline" :
                task = new Deadline(addCommand);
                break;
            case "event":
                task = new Event(addCommand);
                break;
            case "todo" :
                task = Todo.of(addCommand);
                break;
            default:
                throw new IllegalArgumentException();
            }
            assert task != null : "the task cannot be null";
            Task taskAdded = tasks.add(task);
            return ui.showTaskAdded(taskAdded, tasks.getNumTasks());
        } catch (IllegalArgumentException e) {
            throw new DukeException(DukeException.Exceptions.EXCEPTIONS);
        }
    }
}
