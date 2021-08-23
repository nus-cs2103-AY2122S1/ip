package duke.command;

import duke.DukeList;

/**
 * Encapsulate a command on a DukeList.
 */
public class ListCommand implements Command {
    private DukeList duke;

    public ListCommand(DukeList duke) {
        this.duke = duke;
    }

    @Override
    public void exec(String args) {
        this.duke.currentListResponse().print();
    }

    @Override
    public String getLabel() {
        return "list";
    }

}
