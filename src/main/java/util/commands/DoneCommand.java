package util.commands;

import util.tasks.*;
import util.ui.*;
import util.commons.*;

public class DoneCommand implements Command {
    private final Task t;
    private final Ui ui;


    public DoneCommand(Task t, Ui ui) {
        this.t = t;
        this.ui = ui;
    }




    @Override
    public void execute() {
        t.done();
        ui.print(String.format(Messages.TASK_COMPLETE, t.toString()));

    }
}
