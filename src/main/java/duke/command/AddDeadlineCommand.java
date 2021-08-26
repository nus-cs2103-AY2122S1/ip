package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.task.Task;
import duke.task.Deadline;

public class AddDeadlineCommand extends Command {
    private String arguments;

    /**
     * Creates a command that adds a {@link Deadline} to the user's tasks.
     * @param arguments Command arguments.
     */
    public AddDeadlineCommand(String arguments) throws Exception {
        if (arguments.length() == 0) {
            throw new Exception("Command `deadline` requires arguments");
        }
        this.arguments = arguments;
    }

    /**
     * Adds a deadline to the user's tasklist.
     * @param taskList The tasklist.
     * @param ui The instance of the {@link Ui} class.
     * @param storage The instance of the {@link Storage} class.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws Exception {
        Task deadline = Deadline.fromInput(arguments);
        taskList.addTask(deadline);

        storage.saveTasks(taskList);
        ui.printMessage("Added the following task:\n    " + deadline.toString() + "\n" + "You now have "
                + taskList.size() + " tasks in your list.");
    }

    public boolean shouldExit() {
        return false;
    }
}
