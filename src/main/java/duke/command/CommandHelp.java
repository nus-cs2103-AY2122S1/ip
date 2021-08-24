package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class CommandHelp extends Command {
    private static final String HELP_MESSAGE = "List of Commands:\n" +
            "List,\n" +
            "Done,\n" +
            "duke.task.Todo,\n" +
            "duke.task.Deadline,\n" +
            "duke.task.Event,\n" +
            "Delete,\n" +
            "Check,\n" +
            "Bye";

    public static final String KEYWORD = "help";


    @Override
    public void execute(TaskList tl, Storage st, Ui ui) {
        ui.printout(HELP_MESSAGE);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
