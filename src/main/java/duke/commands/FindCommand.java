package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.tasktypes.Task;
import duke.tasktypes.TaskList;

import java.util.ArrayList;

public class FindCommand extends Command {

    private String inputToFind;

    public FindCommand(String inputToFind) {
        this.inputToFind = inputToFind;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            ArrayList<Task> list = new ArrayList<>();
            TaskList listAfterSearch = new TaskList(list);
            for (int i = 0; i < taskList.getSize(); i++) {
                if (taskList.get(i).containsMatch(inputToFind)) {
                    Task taskMatch = taskList.get(i);
                    listAfterSearch.add(taskMatch);
                }
            }
            ui.displayFind(listAfterSearch);
            ui.displayList(listAfterSearch);
        } catch (Exception e) {
            ui.showError(e);
        }
    }
}
