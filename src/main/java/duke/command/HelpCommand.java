package duke.command;

import duke.TaskList;
import duke.ui.Ui;


public class HelpCommand extends Command {
    public HelpCommand() {
    }

    public String execute(TaskList taskList) {
        return Ui.showHelpMessage();
    }

    public String getType() {
        return "help";
    }
}
