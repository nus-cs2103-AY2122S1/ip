package duke.commands;

import duke.DateTimeHandler;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Encapsulates the list command, used to list out all the tasks
 */
public class ListCommand extends Command {

    public ListCommand(String arguments) {
        super("");
    }

    @Override
    public String execute(TaskList tl, Storage s, Ui ui, DateTimeHandler dth) {
        return ui.formatMessage(tl.printList());
    }

    @Override
    public String getCommandPrefix() {
        return "list";
    }

    @Override
    public Command of(String arguments) {
        return new ListCommand(arguments);
    }
}
