package duke.command;

import duke.Duke;
import duke.Parser;
import duke.Ui;

public class ListCommand extends Command{
    public static final String COMMAND_WORD = "list";

    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public void run(Duke duke, Parser parser) {
        Ui.displayTasks(duke.getList());
    }
}
