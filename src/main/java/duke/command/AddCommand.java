package duke.command;

import duke.InvalidDukeCommandException;
import duke.TaskManager;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.DateTimeException;

/**
 * Represents the command to add additional tasks. Accepted task so far are "todo" "deadline" and "event".
 */
public class AddCommand extends Command {
    private final String type;
    private final String[] args;

    public AddCommand(String type, String... args) {
        this.type = type;
        this.args = args;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui) throws InvalidDukeCommandException, DateTimeException {
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

        taskManager.addTask(task);

        ui.reply(String.format(
                "Got it. I've added this task: \n" +
                        "%s\n" +
                        "Now you have %d tasks in the list", task.toString(), taskManager.taskCount()
        ));
    }
}
