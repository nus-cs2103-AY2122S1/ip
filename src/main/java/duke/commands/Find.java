package duke.commands;

import duke.utils.TaskList;

public class Find extends Command {
    private String query;

    public Find(String query) {
        this.query = query;
    }

    @Override
    public TaskList execute(TaskList taskList) {
        return taskList.search(query);
    }
}
