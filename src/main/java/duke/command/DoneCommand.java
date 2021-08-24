package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DoneCommand extends Command {
    public String arguments;

    public DoneCommand(String arguments) {
        super("done");
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (arguments.isEmpty()) {
            throw new DukeException("No index was keyed in. Please try again.");
        }
        int index = Integer.parseInt(arguments);
        if (index < 1 || index > tasks.size()) {
            throw new DukeException("The index you entered is invalid. Please try again.");
        }
        Task taskToBeMarked = tasks.get(index - 1);
        taskToBeMarked.markTaskAsDone();
        ui.printToUser("Nice! I've marked this task as done:");
        ui.printToUser("  " + taskToBeMarked);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
