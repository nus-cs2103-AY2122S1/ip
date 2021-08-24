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
    public void execute(TaskList tl, Storage s, UI ui, DateTimeHandler dth) {
        String args = super.getArguments();
        if (args.length() == 0) {
            ui.print("Please enter a search term after find");
            return;
        }
        String[] results = tl.findInTasks(args);
        ui.print(results);
    }

    @Override
    public String startsWith() {
        return "find";
    }
}
