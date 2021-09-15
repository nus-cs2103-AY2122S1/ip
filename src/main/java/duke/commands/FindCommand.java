package duke.commands;

import duke.DateTimeHandler;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {

    public FindCommand(String arguments) {
        super(arguments);
    }

    @Override
    public Command of(String arguments) {
        return new FindCommand(arguments);
    }

    @Override
    public String execute(TaskList tl, Storage s, Ui ui, DateTimeHandler dth) throws DukeException {
        String args = super.getArguments();
        if (args.length() == 0) {
            throw new DukeException("Please enter a search term after find");

        }
        String[] results = tl.findInTasks(args);
        return ui.formatMessage(results);
    }

    @Override
    public String getCommandPrefix() {
        return "find";
    }
}
