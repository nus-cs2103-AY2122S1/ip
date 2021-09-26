package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.tasktypes.Task;
import duke.tasktypes.TaskList;
import java.util.ArrayList;

public class FindCommand extends Command {

    private final String inputToFind;

    public FindCommand(String inputToFind) {
        this.inputToFind = inputToFind;
    }

    /**
     * Executes the command.
     *
     * @param taskList taskList with all tasks.
     * @param ui User Interface to deal with interactions with user.
     * @param storage Storage to store data of user.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert taskList != null;
        assert ui != null;
        assert storage != null;
        try {
            ArrayList<Task> list = new ArrayList<>();
            TaskList listAfterSearch = new TaskList(list);
            for (int i = 0; i < taskList.getSize(); i++) {
                if (taskList.get(i).containsMatch(inputToFind)) {
                    Task taskMatch = taskList.get(i);
                    listAfterSearch.add(taskMatch);
                }
            }
            return ui.displayFind(listAfterSearch);
        } catch (Exception e) {
            return ui.showError(e);
        }
    }
}
