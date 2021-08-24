package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {

    public String arguments;

    public FindCommand(String arguments) {
        super("find");
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (arguments.isEmpty()) {
            throw new DukeException("No matching string was entered.");
        }
        ArrayList<Task> matchedTasks = new ArrayList<>();
        ui.printToUser("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(arguments)) {
                matchedTasks.add(tasks.get(i));
            }
        }
        if (matchedTasks.isEmpty()) {
            ui.printToUser("  There are no tasks that match your query. Try again.");
        } else {
            for (int i = 0; i < matchedTasks.size(); i++) {
                Task currTask = matchedTasks.get(i);
                ui.printToUser((i + 1) + ". " + currTask);
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
