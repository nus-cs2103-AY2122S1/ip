package duke.command;

import java.util.List;

import duke.DukeList;
import duke.UserInterface;
import duke.task.Task;

/**
 * Represents a command that finds a list of tasks by a given keyword/phrase.
 */
public class FindCommand implements Command {
    private final DukeList dukelist;
    private final UserInterface ui;

    /**
     * Creates a command that finds a task with a given keyword/phrase.
     *
     * @param list with the tasks to search in
     * @param ui   the user interface with which the response should be printed on
     */
    public FindCommand(DukeList list, UserInterface ui) {
        this.dukelist = list;
        this.ui = ui;
    }

    @Override
    public void exec(String args) {
        List<Task> tasks = this.dukelist.findTasksByName(args);
        assert tasks != null;
        this.ui.showTaskList(tasks);
    }

    @Override
    public String getLabel() {
        return "find";
    }

}
