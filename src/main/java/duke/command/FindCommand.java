package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.task.Storage;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> matchingTasks = tasks.findTask(keyword);
        
        if (matchingTasks.size() == 0 ){
            ui.showMessage("No matching tasks found for '" + keyword + "'. Try another keyword.");
            return;
        }
        ui.showMessage("I found these matching tasks in your list for '" + keyword + "':");
        for (int i = 0; i < matchingTasks.size(); i++) {
            Task task = matchingTasks.get(i);
            int num = i+1;
            ui.showMessage((num + "." + task.toString()));
        }
    }

    public boolean isExit() {
        return false;
    }
}
