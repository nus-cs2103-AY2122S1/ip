package duke.command;

import java.util.ArrayList;

import duke.error.DukeException;
import duke.general.Storage;
import duke.general.Tasklist;
import duke.general.Ui;
import duke.task.Task;

/**
 * Command to find certain keywords in list of tasks
 */
public class FindCommand extends Command {
    private String[] input;

    public FindCommand(String[] inputSplit) {
        this.input = inputSplit;
    }

    @Override
    public String execute(Tasklist tasks, Storage storage, Ui ui) throws DukeException {
        if (input.length < 2) {
            throw new DukeException("Please add in a search keyword!!");
        }
        ArrayList<Task> foundList = tasks.findTasks(input[1]);
        return ui.findResponse(foundList);
    }
}
