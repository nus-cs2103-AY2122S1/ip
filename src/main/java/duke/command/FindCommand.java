package duke.command;

import java.util.ArrayList;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Contains the executables when the user uses the 'Find' command.
 *
 * @author Benjamin Lui
 */
public class FindCommand extends Command {
    private String[] keyword;
    /**
     * Constructor for the find command.
     * @param keyword the keyword to search for
     */
    public FindCommand(String ... keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the Find command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> res = new ArrayList<Task>();
        for (String keyword : this.keyword) {
            tasks.getAllTasks()
                    .stream()
                    .forEach((tsk) -> {
                        String currTask = tsk.toString();
                        if (currTask.contains(keyword)) {
                            res.add(tsk);
                        }
                    });
        }
        return ui.matchingTasks() + ui.listView(res);
    }
}
