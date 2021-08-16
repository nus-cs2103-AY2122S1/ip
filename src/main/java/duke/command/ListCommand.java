package duke.command;

import duke.Duke;

/**
 * Encapsulate a command on a DukeList.
 */
public class ListCommand implements Command {
    @Override
    public void exec(String args) {
        Duke.getList().currentListResponse().print();
    }

    @Override
    public String getLabel() {
        return "list";
    }
}
