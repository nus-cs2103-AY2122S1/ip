package duke.commands;

import duke.TaskList;

/**
 * This class handles command meant for finding tasks.
 */
public class FindCommand implements Command {

    private String searchString;

    /**
     * Constructor which takes in a substring to search for in descriptions of tasks.
     *
     * @param searchString String that is the search term.
     */
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
}
