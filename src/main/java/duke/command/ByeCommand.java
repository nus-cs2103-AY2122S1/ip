package duke.command;

import duke.ui.Ui;

public class ByeCommand extends Command{
    private final Ui textUi;

    public ByeCommand(Ui ui) {
        this.textUi = ui;
    };

    @Override
    public String execute() {
        return textUi.bye();
    }
}
