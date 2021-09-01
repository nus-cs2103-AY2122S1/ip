package duke.commands;

import duke.TaskList;

public class FindCommand implements Command {

    private String searchString;

    public FindCommand(String searchString) {
        this.searchString = searchString;
    }

    @Override
    public String execute(TaskList taskList) {
        String output = "Here are the matching tasks in your list:\n" + taskList.findTask(searchString);
        if (taskList.isTaskFound()) {
            return output;
        } else {
            return "I'm sorry, but I can't find this task :-(";
        }
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
