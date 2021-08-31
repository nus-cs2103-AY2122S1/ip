package duke.command;

import duke.TaskList;

public class ListCommand extends Command {

    private final TaskList dukeList;

    public ListCommand(TaskList dukeList) {
        this.dukeList = dukeList;
    }

    @Override
    public String getResponse(String input) {
        return dukeList.showList();
    }
}
