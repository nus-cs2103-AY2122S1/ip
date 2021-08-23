package duke.command;

import duke.Duke;

/**
 * Encapsulate a command on a DukeList.
 */
public class ListCommand implements Command {
    private Duke duke;

    public ListCommand(Duke duke) {
        this.duke = duke;
    }

    @Override
    public void exec(String args) {
        this.duke.getList().currentListResponse().print();
    }

    @Override
    public String getLabel() {
        return "list";
    }

}
