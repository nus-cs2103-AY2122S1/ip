package duke.command;

import duke.error.DukeException;
import duke.general.Storage;
import duke.general.Tasklist;
import duke.general.Ui;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Command to find certain keywords in list of tasks
 */
public class FindCommand extends Command {
    private String[] input;

    public FindCommand(String[] inputSplit) {
        this.input = inputSplit;
    }

    @Override
    public void execute(Tasklist tasks, Storage storage, Ui ui) throws DukeException {
        ArrayList<Task> foundList = tasks.findTasks(input[1]);
        ui.findResponse(foundList);
    }
}
