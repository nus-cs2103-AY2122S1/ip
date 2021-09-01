package duke.commands;

import duke.TaskList;

/**
 * This class handles command meant for retrieving a list of current existing tasks.
 */
public class ListCommand implements Command {

    @Override
    public String execute(TaskList taskList) {
        return taskList.getList();
    }
}
