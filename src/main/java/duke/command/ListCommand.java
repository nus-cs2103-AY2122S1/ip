package duke.command;

import duke.DukeList;

/**
 * Encapsulate a command on a DukeList.
 */
public class ListCommand implements Command {
    private final DukeList list;

    public ListCommand(DukeList list) {
        this.list = list;
    }

    @Override
    public String getName() {
        return "list";
    }

    @Override
    public void exec(String args) {
        this.list.currentListResponse().print();
    }
}
