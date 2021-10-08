package duke.command;

import java.util.List;

import duke.storage.Storage;
import duke.storage.TaskList;
import duke.task.Task;
import duke.ui.UiPane;

public class FindCommand extends Command {
    private final String query;

    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, UiPane uiPane) {
        List<Task> matchingTasks = taskList.findMatchingTasks(query);
        uiPane.showTaskList(matchingTasks);
        uiPane.showMessage(
                String.format(
                        "We have found %d matching tasks. Execute the \"list\" command to show the full list again.",
                        matchingTasks.size()
                )
        );
    }
}
