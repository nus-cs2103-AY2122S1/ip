package duke.commands;

import java.util.ArrayList;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

public class FindCommand extends Command {
    private String searchDescription;

    public FindCommand(String s) {
        this.searchDescription = s;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> results = tasks.findTask(this.searchDescription);
        return ui.showFindResults(results);
    }
}
