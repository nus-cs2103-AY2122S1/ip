package duke.command;

import java.time.DateTimeException;

import duke.DukeException;
import duke.InvalidDukeCommandException;
import duke.TaskManager;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Represents the command to add additional tasks. Accepted task so far are "todo" "deadline" and "event".
 */
public class AddCommand extends Command {
    private final String type;
    private final String[] args;

    /**
     * Constructs AddCommand object.
     *
     * @param type the type of task to be added.
     * @param args the argument for the task to be added.
     */
    public AddCommand(String type, String... args) {
        assert type != null : "command type should not be null";
        assert args != null : "command args should not be null";
        this.type = type;
        this.args = args;
    }

    @Override
    public String execute(TaskManager taskManager) throws DukeException, DateTimeException {
        Task task;
        switch (type) {
        case "todo":
            task = ToDo.of(false, args[0]);
            break;
        case "deadline":
            task = Deadline.of(false, args[0], args[1]);
            break;
        case "event":
            task = Event.of(false, args[0], args[1]);
            break;
        default:
            throw new InvalidDukeCommandException();
        }
        assert task != null : "A task should not be null, either exception thrown or task created";
        taskManager.addTask(task);

        return String.format("Got it. I've added this task: \n"
                + "%s\n"
                + "Now you have %d tasks in the list", task.toString(), taskManager.getNumOfTasks()
        );
    }
}
