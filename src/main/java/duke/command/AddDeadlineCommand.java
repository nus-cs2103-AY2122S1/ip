package duke.command;

import duke.DateTime;
import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.task.Deadline;
import duke.task.Task;
import duke.ui.Ui;

import java.time.LocalDateTime;

public class AddDeadlineCommand extends Command {
    private String description;
    private LocalDateTime time;

    /**
     * Creates a command that adds a {@link Deadline} to the user's tasks.
     *
     * @param arguments Command arguments.
     */
    public AddDeadlineCommand(String arguments) throws DukeException {
        if (arguments.length() == 0) {
            throw new InvalidCommandException("Command `deadline` requires arguments");
        }

        String[] deadlineInputs = arguments.trim().split("\\s+/by\\s+", 2);

        if (deadlineInputs.length == 1) {
            if (deadlineInputs[0].length() == 0) {
                throw new InvalidCommandException("Deadline must have description and time");
            } else {
                throw new InvalidCommandException("Deadline must have time");
            }
        }

        if (deadlineInputs.length != 2) {
            throw new InvalidCommandException("Deadline must have description and time");
        }

        this.description = deadlineInputs[0];
        this.time = DateTime.parse(deadlineInputs[1]);
    }

    /**
     * Adds a deadline to the user's tasklist.
     *
     * @param taskList The tasklist.
     * @param ui The instance of the {@link Ui} class.
     * @param storage The instance of the {@link Storage} class.
     * @throws IOException when unable to save tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws Exception {
        Task deadline = new Deadline(description, time);
        taskList.addTask(deadline);

        storage.saveTasks(taskList);
        ui.printMessage("Added the following task:\n    " + deadline.toString() + "\n" + "You now have "
                + taskList.size() + " tasks in your list.");
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}
