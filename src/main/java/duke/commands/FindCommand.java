package duke.commands;

import duke.TaskList;
import duke.Ui;

public class FindCommand implements Command {

    private String searchString;

    public FindCommand(String searchString) {
        this.searchString = searchString;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        String output = "Here are the matching tasks in your list:\n" + taskList.findTask(searchString);
        if (taskList.isTaskFound()) {
            ui.stringWithDivider(output);
        } else {
            ui.stringWithDivider("I'm sorry, but I can't find this task :-(");
        }
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
