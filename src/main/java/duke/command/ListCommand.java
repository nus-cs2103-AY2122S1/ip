package duke.command;

import duke.DukeList;
import duke.UserInterface;

/**
 * Encapsulate a command on a DukeList.
 */
public class ListCommand implements Command {
    private final DukeList dukeList;
    private final UserInterface ui;

    public ListCommand(DukeList dukeList, UserInterface ui) {
        this.dukeList = dukeList;
        this.ui = ui;
    }

    @Override
    public void exec(String args) {
        this.ui.showObject(this.dukeList);
    }

    @Override
    public String getLabel() {
        return "list";
    }

}
