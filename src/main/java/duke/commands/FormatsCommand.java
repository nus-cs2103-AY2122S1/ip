package duke.commands;

import duke.DateTimeHandler;
import duke.Storage;
import duke.TaskList;
import duke.UI;

public class FormatsCommand extends Command {

    public FormatsCommand(String arguments) {
        super(arguments);
    }

    @Override
    public Command of(String arguments) {
        return new FormatsCommand("");
    }

    @Override
    public void execute(TaskList tl, Storage s, UI ui, DateTimeHandler dth) {
        ui.print(dth.getFormatList());
    }

    @Override
    public String startsWith() {
        return "formats";
    }
}
