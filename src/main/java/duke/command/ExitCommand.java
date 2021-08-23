package duke.command;


import duke.Ui;

public class ExitCommand extends Command{

    public Ui ui;

    public ExitCommand(Ui ui) {
        this.ui = ui;
    }

    @Override
    public void execute(){
        ui.end();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
