package duke.command;

import java.util.ArrayList;

import duke.exception.NoActionException;
import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class FindCommand extends Command {
    private final String searchTerms;

    /**
     * Constructor for the find command.
     *
     * @param searchTerms Word to search for.
     */
    public FindCommand(String searchTerms) {
        super(false);
        this.searchTerms = searchTerms;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NoActionException {
        if (this.searchTerms.length() == 0) {
            throw new NoActionException("Command 'find' requires search terms to be provided.");
        }
        ArrayList<Task> matchList = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            if (currTask.contains(searchTerms)) {
                matchList.add(currTask);
            }
        }
        ui.showMatchingTasks(new TaskList(matchList));
    }

}
