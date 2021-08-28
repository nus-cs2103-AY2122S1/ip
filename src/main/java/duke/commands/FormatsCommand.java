package duke.commands;

import duke.DateTimeHandler;
import duke.Storage;
import duke.TaskList;
import duke.UI;

/**
 * Encapsulates the formats command, used to print the valid date-time formats
 */
public class FormatsCommand extends Command {

    public FormatsCommand(String arguments) {
        super(arguments);
    }

    @Override
    public Command of(String arguments) {
        return new FormatsCommand("");
    }

    @Override
    public String execute(TaskList tl, Storage s, UI ui, DateTimeHandler dth) {
        return ui.formatMessage(dth.getFormatList());
    }

    @Override
    public String startsWith() {
        return "formats";
    }
}
