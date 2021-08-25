package duke.commands;

import duke.functionality.Storage;
import duke.functionality.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the command when the user wants to find a task based on a keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand (String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command and finds saved tasks that match the keyword input by the user.
     *
     * @param storage The storage system of the bot involved with this command.
     * @param ui The ui of the bot involved with this command.
     */
    public void execute(Storage storage, Ui ui) {
        TaskList taskList = storage.getTaskList();
        List<Task> listOfMatches = new ArrayList<Task>();
        for (int i = 0; i < taskList.taskListLen(); i++) {
            Task curr = taskList.getTask(i);
            if (searchKeyword(keyword, curr)) {
                listOfMatches.add(curr);
            }
        }
        ui.printMatchingTasksMessage(listOfMatches);
    }

    private boolean searchKeyword(String keyword, Task task) {
        return task.taskName().contains(keyword);
    }

    public boolean isExit() {
        return false;
    }
}
