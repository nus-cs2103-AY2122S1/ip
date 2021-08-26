package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.tasks.Task;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FindCommand extends Command {
    private String searchDescription;
    
    public FindCommand(String s) {
        this.searchDescription = s;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> results = tasks.findTask(this.searchDescription);
        ui.showFindResults(results);
    }
}
