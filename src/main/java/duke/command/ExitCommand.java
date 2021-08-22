package duke.command;

import duke.Ui;

public class ExitCommand extends Command{

    @Override
    public void execute() {
        Ui.myPrint("Bye. Hope to see you again soon!");
    }
}
