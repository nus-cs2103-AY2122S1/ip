package duke.command;

import duke.DukeList;
import duke.UserInterface;

/**
 * Represents a command to list all tasks in a dukelist.
 */
public class ListCommand implements Command {
    private final DukeList dukeList;
    private final UserInterface ui;

    /**
     * Creates a command that lists tasks in the given dukelist onto the specified
     * ui.
     *
     * @param dukeList of which tasks
     * @param ui       to display the tasks onto
     */
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
