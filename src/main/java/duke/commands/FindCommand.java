package duke.commands;

import duke.DateTimeHandler;
import duke.Storage;
import duke.TaskList;
import duke.UI;

public class FindCommand extends Command {

    public FindCommand(String arguments) {
        super(arguments);
    }

    @Override
    public Command of(String arguments) {
        return new FindCommand(arguments);
    }

    @Override
    public String execute(TaskList tl, Storage s, UI ui, DateTimeHandler dth) {
        String args = super.getArguments();
        if (args.length() == 0) {
            return "Please enter a search term after find";

        }
        String[] results = tl.findInTasks(args);
        return ui.formatMessage(results);
    }

    @Override
    public String startsWith() {
        return "find";
    }
}
